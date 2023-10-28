package io.esoma.cbj.ds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * A key-value store implemented with an open-addressing hash table where collided entries are
 * stored in the empty slots of the table. Such table cannot be too loaded, otherwise the
 * performance will degrade dramatically. The table will expand and shrink dynamically based on
 * load. Hashing is done using the default implementation of the key type in Java, unless overridden
 * by the client code. Null keys are not permitted.
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class LinearProbingHashTable<K, V> {

  // How full can the table be in percentage.
  private static final int LOAD_FACTOR_PCT = 50;

  private final int minCapacity;
  private int size;
  // ArrayList is used over an array to cope with generics.
  private ArrayList<Entry<K, V>> entries;

  public LinearProbingHashTable() {
    this(10);
  }

  public LinearProbingHashTable(int initialCapacity) {
    // Reserve at least 4 slots.
    if (initialCapacity < 4) {
      initialCapacity = 4;
    }

    this.minCapacity = initialCapacity;
    this.entries = createTable(initialCapacity);
  }

  /**
   * Creates a new empty table for the specified capacity. It will be filled with null elements.
   *
   * @param capacity the table size
   * @return a new null-filled ArrayList representing the table
   */
  private ArrayList<Entry<K, V>> createTable(int capacity) {
    ArrayList<Entry<K, V>> table = new ArrayList<>();
    for (int i = 0; i < capacity; ++i) {
      table.add(null);
    }
    return table;
  }

  /**
   * Returns the number of entries in the table.
   *
   * @return the table's size
   */
  public int size() {
    return size;
  }

  /**
   * Puts a key into the table with the associated value. If the key already exists, the value will
   * be overwritten.
   *
   * @param key the key to insert
   * @param value the value associated
   * @return true if a new entry is created, or false otherwise
   */
  public boolean put(K key, V value) {
    if (key == null) {
      throw new IllegalArgumentException("Key may not be null");
    }

    int slot = findSlot(key);
    if (entries.get(slot) == null) {
      // Didn't find the key, thus insert a new entry.
      entries.set(slot, new Entry<>(key, value));
      ++size;

      // Check load and expand the table if needed.
      int capacity = entries.size();
      if (size * 100 / capacity >= LOAD_FACTOR_PCT) {
        expand();
      }

      return true;
    } else {
      // Found the key, thus replace the value.
      entries.get(slot).value = value;
      return false;
    }
  }

  /**
   * Inserts a key value pair directly into the table without updating other states or performing
   * other checks. This is used internally only to rehash an existing key.
   *
   * @param key the key to insert
   * @param value the value associated
   */
  private void putInternal(K key, V value) {
    int slot = findSlot(key);
    if (entries.get(slot) == null) {
      entries.set(slot, new Entry<>(key, value));
    }
  }

  /**
   * Checks if the specified key exists in the table.
   *
   * @param key the key to check
   * @return whether the key exists in the table
   */
  public boolean containsKey(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key may not be null");
    }

    return entries.get(findSlot(key)) != null;
  }

  /**
   * Retrieves the value associated with the specified key. If the key doesn't exist, null will be
   * returned.
   *
   * @param key the key to retrieve value for
   * @return the associated value, or null if not existent
   */
  public V get(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key may not be null");
    }

    int slot = findSlot(key);
    if (entries.get(slot) == null) {
      return null;
    } else {
      return entries.get(slot).value;
    }
  }

  /**
   * Deletes the key from the table with its associated value.
   *
   * @param key the key to delete
   * @return true if a key is deleted, or false otherwise
   */
  public boolean delete(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key may not be null");
    }

    int slot = findSlot(key);
    if (entries.get(slot) == null) {
      return false;
    }

    // Nullify the target entry first.
    entries.set(slot, null);

    // Search to the right and re-insert everything until an empty slot is found.
    // This step is needed so we don't prematurely terminate the search for a key that was inserted
    // later than the deleted one. In other words, we cannot artificially create gaps between
    // entries. Gaps must only exist naturally.
    while (true) {
      slot = nextSlot(slot);
      Entry<K, V> entry = entries.get(slot);
      if (entry == null) {
        break;
      } else {
        entries.set(slot, null);
        putInternal(entry.key, entry.value);
      }
    }

    --size;

    // Check load and shrink the table if needed.
    // Shrink table if the fullness is less than half of the load factor, and the table has at least
    // the initial capacity after shrinking.
    int capacity = entries.size();
    if ((size * 100 / capacity < LOAD_FACTOR_PCT / 2) && (capacity > minCapacity * 2)) {
      shrink();
    }

    return true;
  }

  @Override
  public String toString() {
    return "LinearProbingHashTable{" + "entries=" + allEntries() + '}';
  }

  private Collection<Entry<K, V>> allEntries() {
    return entries.stream().filter(Objects::nonNull).toList();
  }

  /**
   * Finds the slot (index into the flat table) where the specified key is to be inserted. If the
   * key exists, its current slot will be returned.
   *
   * @param key the key to find slot for
   * @return the table index where the key resides, or an empty slot for insertion
   */
  private int findSlot(K key) {
    // Multiply by a prime number spreads out keys very well.
    // This avoids the worse case of long sequential keys being inserted.
    int slot = Math.abs(key.hashCode() * 997 % entries.size());

    while (entries.get(slot) != null) {
      if (entries.get(slot).key.equals(key)) {
        // Desired entry is found.
        break;
      }
      slot = nextSlot(slot);
    }

    return slot;
  }

  /**
   * Fetches the next slot (index into the flat table) given the current slot. It wraps around to
   * the beginning if the last slot is reached.
   *
   * @param slot the current slot
   * @return the next slot in the table
   */
  private int nextSlot(int slot) {
    if (slot >= entries.size() - 1) {
      return 0;
    }
    return slot + 1;
  }

  /**
   * Doubles the capacity of the table to accommodate for more keys. Existing keys will be
   * re-inserted.
   */
  private void expand() {
    ArrayList<Entry<K, V>> oldTable = entries;
    this.entries = createTable(oldTable.size() * 2);

    for (Entry<K, V> entry : oldTable) {
      if (entry != null) {
        // Re-insert into the table.
        putInternal(entry.key, entry.value);
      }
    }
  }

  /** Halves the capacity of the table to save memory. Existing keys will be re-inserted. */
  private void shrink() {
    ArrayList<Entry<K, V>> oldTable = entries;
    this.entries = createTable(oldTable.size() / 2);

    for (Entry<K, V> entry : oldTable) {
      if (entry != null) {
        // Re-insert into the table.
        putInternal(entry.key, entry.value);
      }
    }
  }

  /**
   * A data structure that holds a key-value pair in the hash table. The key is immutable once
   * created and cannot be null.
   *
   * @param <K> the type of the key
   * @param <V> the type of the value
   */
  private static class Entry<K, V> {

    final K key;
    V value;

    Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String toString() {
      return "Entry{" + "key=" + key + ", value=" + value + '}';
    }
  }
}
