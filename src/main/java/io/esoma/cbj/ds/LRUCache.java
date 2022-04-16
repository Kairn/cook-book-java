package io.esoma.cbj.ds;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for implementing the LRU (Least Recently Used) cache scheme. A LRU cache stores data in
 * key-value pairs, each key uniquely identifies a value. Duplicate keys are not allowed in LRU
 * cache. A capacity is set beforehand to prevent the cache from growing beyond desired upper memory
 * limit. When LRU cache has no more capacity for new entries, the element that is least recently
 * interacted will be deleted to store the new element. Such behavior tries to maximize cache hit
 * rate by keeping popular items in the cache.
 *
 * <p>In particular, this example class always use integers as keys, and the values can be any type
 * specified by the user during object creation. However, null values are not permitted as it
 * conflicts with the return of a cache miss.
 */
public class LRUCache<V> {

  private static final int DEFAULT_CAPACITY = 20;

  private final int capacity;
  private int size = 0;

  private final Map<Integer, EntryNode<V>> internalMap;
  private EntryNode<V> head;
  private EntryNode<V> tail;

  /**
   * Constructs a new LRUCache object with the specified capacity.
   *
   * @param capacity the limit of the cache size
   */
  public LRUCache(int capacity) {
    if (capacity <= 0) {
      this.capacity = DEFAULT_CAPACITY;
    } else {
      this.capacity = capacity;
    }
    this.internalMap = new HashMap<>();
  }

  // TODO: Add clear method and test it.

  /**
   * Inserts a value into the cache identified by the specified key. If a key already exists, the
   * old value will be replaced. Replacement counts as access. Exception is thrown if the provided
   * value is null.
   *
   * @param key the key for storing the value
   * @param value the associated value to store
   * @return true if the key does not exist in the cache previously, or false if a replacement
   *     occurs
   */
  public boolean put(int key, V value) {
    if (value == null) {
      throw new IllegalArgumentException("Null value is not permitted");
    }

    if (internalMap.containsKey(key)) {
      EntryNode<V> existingNode = internalMap.get(key);
      existingNode.value = value;
      makeHead(existingNode);
      return false;
    } else {
      EntryNode<V> newNode = new EntryNode<>(key, value);
      internalMap.put(key, newNode);
      if (size < capacity) {
        // Add node to the head
        addToHead(newNode);
        ++size;
      } else {
        // Cut the tail then add node to the head
        cutTail();
        addToHead(newNode);
      }
      return true;
    }
  }

  /**
   * Accesses the key and returns its value if it exists, and this key will be promoted as the most
   * recently used key. Null will be returned for non-existent keys.
   *
   * @param key the key to retrieve the value
   * @return the associated value, or null if the key is not found in the cache
   */
  public V get(int key) {
    if (internalMap.containsKey(key)) {
      EntryNode<V> existingNode = internalMap.get(key);
      makeHead(existingNode);
      return existingNode.value;
    }
    return null;
  }

  private void addToHead(EntryNode<V> node) {
    node.next = head;
    if (head != null) {
      head.prev = node;
    } else {
      // First node is also the tail
      tail = node;
    }
    head = node;
  }

  private void cutTail() {
    if (tail != null) {
      internalMap.remove(tail.key);
      tail = tail.prev;
      if (tail != null) {
        tail.next = null;
      }
    }
  }

  private void makeHead(EntryNode<V> node) {
    EntryNode<V> nextNode = node.next;
    EntryNode<V> prevNode = node.prev;
    node.next = null;
    node.prev = null;

    if (nextNode != null && prevNode != null) {
      prevNode.next = nextNode;
      nextNode.prev = prevNode;
      addToHead(node);
    } else if (prevNode != null) {
      // Node is the tail
      tail = prevNode;
      addToHead(node);
    }

    // Node is already the head otherwise
  }

  private static class EntryNode<V> {

    final int key;
    V value;

    EntryNode<V> prev;
    EntryNode<V> next;

    EntryNode(int key, V value) {
      this.key = key;
      this.value = value;
    }
  }
}
