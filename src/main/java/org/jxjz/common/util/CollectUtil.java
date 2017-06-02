package org.jxjz.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.UnhandledException;

public final class CollectUtil
{
  public static <E> List<E> newArrayList()
  {
    return new ArrayList();
  }

  public static <E> List<E> newArrayList(int initialCapacity) {
    return new ArrayList(initialCapacity);
  }

  public static <E> List<E> newArrayList(Collection<? extends E> c) {
    return new ArrayList(c);
  }

  public static <E> List<E> newArrayList(E[] values) {
    List list = new ArrayList(values.length);
    for (Object e : values) {
      list.add(e);
    }
    return list;
  }

  public static <T> List<List<T>> split(List<T> list, int count)
  {
    List subIdLists = newArrayList();
    if (list.size() < count) {
      subIdLists.add(list);
    } else {
      int i = 0;
      while (i < list.size()) {
        int end = i + count;
        if (end > list.size()) {
          end = list.size();
        }
        subIdLists.add(list.subList(i, end));
        i += count;
      }
    }
    return subIdLists;
  }

  public static <K, V> Map<K, V> newHashMap() {
    return new HashMap();
  }

  public static <K, V> Map<K, V> newConcurrentHashMap() {
    return new ConcurrentHashMap();
  }

  public static <E> Queue<E> newConcurrentLinkedQueue() {
    return new ConcurrentLinkedQueue();
  }

  public static <K, V> Map<K, V> newHashMap(Map<? extends K, ? extends V> m) {
    return new HashMap(m);
  }

  public static <K, V> Map<K, V> newLinkedHashMap(Map<? extends K, ? extends V> m) {
    return new LinkedHashMap(m);
  }

  public static <K, V> Map<K, V> newLinkedHashMap(int size) {
    return new LinkedHashMap(size);
  }

  public static <E> Set<E> newHashSet() {
    return new HashSet();
  }

  public static <E> Set<E> newHashSet(E[] values) {
    Set set = new HashSet(values.length);
    for (Object e : values) {
      set.add(e);
    }
    return set;
  }

  public static <E> Set<E> newHashSet(Collection<? extends E> c) {
    return new HashSet(c);
  }

  public static Map<?, ?> convertToMap(Collection<?> coll, String keyProperty) {
    Map map = newHashMap();
    for (Iterator i$ = coll.iterator(); i$.hasNext(); ) { Object obj = i$.next();
      Object key = null;
      try {
        key = PropertyUtils.getProperty(obj, keyProperty);
      } catch (Exception e) {
        throw new UnhandledException(e);
      }
      map.put(key, obj);
    }
    return map;
  }

  public static Map<?, ?> convertToMap(Collection<?> coll, String keyProperty, String valueProperty) {
    Map map = newHashMap();
    for (Iterator i$ = coll.iterator(); i$.hasNext(); ) { Object obj = i$.next();
      Object key = null;
      Object value = null;
      try {
        key = PropertyUtils.getProperty(obj, keyProperty);
        value = PropertyUtils.getProperty(obj, valueProperty);
      } catch (Exception e) {
        throw new UnhandledException(e);
      }
      map.put(key, value);
    }
    return map;
  }

  public static Map<String, String> toMap(String[][] wordMappings) {
    Map mappings = new HashMap();
    for (int i = 0; i < wordMappings.length; i++) {
      String singular = wordMappings[i][0];
      String plural = wordMappings[i][1];
      mappings.put(singular, plural);
    }
    return mappings;
  }
}