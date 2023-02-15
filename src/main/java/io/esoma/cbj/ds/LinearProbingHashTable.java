package io.esoma.cbj.ds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

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

  private int size;
  // ArrayList is used over an array to cope with generics.
  private ArrayList<Entry<K, V>> entries;

  public LinearProbingHashTable() {
    this(10);
  }

  public LinearProbingHashTable(int initialCapacity) {
    if (initialCapacity < 4) {
      initialCapacity = 4;
    }
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
      this.entries.add(null);
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
      entries.set(slot, new Entry<>(key, value));
      ++size;
      return true;
    } else {
      entries.get(slot).value = value;
      return false;
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
    return false;
  }

  @Override
  public String toString() {
    return "LinearProbingHashTable{" + "entries=" + allEntries() + '}';
  }

  public Collection<Entry<K, V>> allEntries() {
    return entries.stream().filter(Objects::nonNull).collect(Collectors.toList());
  }

  /**
   * Finds the slot (index into the flat table) where the specified key is to be inserted. If the
   * key exists, its current slot will be returned.
   *
   * @param key the key to find slot for
   * @return the table index where the key resides, or an empty slot for insertion
   */
  private int findSlot(K key) {
    return -1;
  }

  /**
   * Doubles the capacity of the table to accommodate for more keys. Existing keys will be
   * re-inserted.
   */
  private void expand() {
    ArrayList<Entry<K, V>> oldTable = entries;
    this.entries = createTable(oldTable.size() * 2);

    for (Entry<K, V> entry : entries) {
      if (entry != null) {
        // Re-insert into the table.
        put(entry.key, entry.value);
      }
    }
  }

  /** Halves the capacity of the table to save memory. Existing keys will be re-inserted. */
  private void shrink() {
    ArrayList<Entry<K, V>> oldTable = entries;
    this.entries = createTable(oldTable.size() / 2);

    for (Entry<K, V> entry : entries) {
      if (entry != null) {
        // Re-insert into the table.
        put(entry.key, entry.value);
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
