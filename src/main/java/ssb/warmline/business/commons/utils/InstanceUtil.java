//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class InstanceUtil {
    private InstanceUtil() {
    }

    public static void copyProperties(Object pFromObj, Object pToObj) {
        copyProperties(pFromObj, pToObj, true);
    }

    public static void copyProperties(Object pFromObj, Object pToObj, boolean isNotNull) {
        if(pFromObj != null && pToObj != null) {
            if(pFromObj instanceof Map && pToObj instanceof Map) {
                ((Map)pToObj).putAll((Map)pFromObj);
            } else if(isNotNull) {
                if(pToObj instanceof Map) {
                    try {
                        BeanUtils.populate(pToObj, (Map)pFromObj);
                    } catch (Exception var11) {
                        throw new InstanceException(var11);
                    }
                } else {
                    Class<?> tc = pToObj.getClass();
                    Field[] fields = tc.getDeclaredFields();
                    Object value = null;

                    for(int i = 0; i < fields.length; ++i) {
                        if(pFromObj instanceof Map) {
                            value = ((Map)pFromObj).get(fields[i].getName());
                        } else {
                            try {
                                value = PropertyUtils.getProperty(pFromObj, fields[i].getName());
                            } catch (Exception var10) {
                                throw new InstanceException(var10);
                            }
                        }

                        if(value != null) {
                            try {
                                value = TypeParseUtil.convert(value, fields[i].getType(), (String)null);
                                PropertyUtils.setProperty(pToObj, fields[i].getName(), value);
                            } catch (Exception var9) {
                                throw new InstanceException(var9);
                            }
                        }
                    }
                }
            } else {
                try {
                    PropertyUtils.copyProperties(pToObj, pFromObj);
                } catch (Exception var8) {
                    throw new InstanceException(var8);
                }
            }
        }

    }

    public static void copyProperties(String prefix, String suffix, Object pFromObj, Object pToObj) {
        if(pFromObj != null && pToObj != null) {
            if(pToObj instanceof Map) {
                try {
                    BeanUtils.populate(pToObj, (Map)pFromObj);
                } catch (Exception var12) {
                    throw new InstanceException(var12);
                }
            } else {
                Class<?> tc = pToObj.getClass();
                Field[] fields = tc.getDeclaredFields();
                Object value = null;
                String key = null;

                for(int i = 0; i < fields.length; ++i) {
                    if(prefix != null) {
                        key = prefix + fields[i].getName();
                    } else {
                        key = fields[i].getName();
                    }

                    if(suffix != null) {
                        key = key + suffix;
                    }

                    if(pFromObj instanceof Map) {
                        value = ((Map)pFromObj).get(key);
                    } else {
                        try {
                            value = PropertyUtils.getProperty(pFromObj, key);
                        } catch (Exception var11) {
                            throw new InstanceException(var11);
                        }
                    }

                    if(value != null && !"".equals(value)) {
                        try {
                            value = TypeParseUtil.convert(value, fields[i].getType(), (String)null);
                            PropertyUtils.setProperty(pToObj, fields[i].getName(), value);
                        } catch (Exception var10) {
                            System.out.println(value);
                            throw new InstanceException(var10);
                        }
                    }
                }
            }
        }

    }

    public static Class<?> getClass(String clazz) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            return loader != null?Class.forName(clazz, true, loader):Class.forName(clazz);
        } catch (ClassNotFoundException var3) {
            throw new InstanceException(var3);
        }
    }

    public static <E> List<E> getInstanceList(Class<E> cls, List<?> list) {
        List<E> resultList = newArrayList();
        E object = null;
        Iterator iterator = list.iterator();

        while(iterator.hasNext()) {
            Map<?, ?> map = (Map)iterator.next();
            object = newInstance(cls, new Object[]{map});
            resultList.add(object);
        }

        return resultList;
    }

    public static <E> List<E> getInstanceList(Class<E> cls, ResultSet rs) {
        ArrayList resultList = newArrayList();

        try {
            E object = cls.newInstance();
            Field[] fields = cls.getDeclaredFields();

            while(rs.next()) {
                object = cls.newInstance();

                for(int i = 0; i < fields.length; ++i) {
                    String fieldName = fields[i].getName();
                    PropertyUtils.setProperty(object, fieldName, rs.getObject(fieldName));
                }

                resultList.add(object);
            }

            return resultList;
        } catch (Exception var7) {
            throw new InstanceException(var7);
        }
    }

    public static <E> E newInstance(Class<E> cls, Map<String, ?> map) {
        Object object = null;

        try {
            object = cls.newInstance();
            BeanUtils.populate(object, map);
            return (E) object;
        } catch (Exception var4) {
            throw new InstanceException(var4);
        }
    }

    public static Object newInstance(String clazz) {
        try {
            return getClass(clazz).newInstance();
        } catch (Exception var2) {
            throw new InstanceException(var2);
        }
    }

    public static <K> K newInstance(Class<K> cls, Object... args) {
        try {
            Class[] argsClass = null;
            if(args != null) {
                argsClass = new Class[args.length];
                int i = 0;

                for(int j = args.length; i < j; ++i) {
                    argsClass[i] = args[i].getClass();
                }
            }

            Constructor<K> cons = cls.getConstructor(argsClass);
            return cons.newInstance(args);
        } catch (Exception var5) {
            throw new InstanceException(var5);
        }
    }

    public static Object newInstance(String className, Object... args) {
        try {
            Class<?> newoneClass = Class.forName(className);
            return newInstance(newoneClass, args);
        } catch (Exception var3) {
            throw new InstanceException(var3);
        }
    }

    public static Object invokeMethod(Object owner, String methodName, Object[] args) {
        Class<?> ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];
        int i = 0;

        for(int j = args.length; i < j; ++i) {
            if(args[i] != null) {
                argsClass[i] = args[i].getClass();
            } else {
                argsClass[i] = "".getClass();
            }
        }

        try {
            Method method = ownerClass.getMethod(methodName, argsClass);
            return method.invoke(owner, args);
        } catch (Exception var7) {
            throw new InstanceException(var7);
        }
    }

    public static Object invokeMethod(Object owner, String methodName, Collection<Object> args) {
        return invokeMethod(owner, methodName, args.toArray());
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList();
    }

    public static <k, v> HashMap<k, v> newHashMap() {
        return new HashMap();
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet();
    }

    public static <k, v> Hashtable<k, v> newHashtable() {
        return new Hashtable();
    }

    public static <k, v> LinkedHashMap<k, v> newLinkedHashMap() {
        return new LinkedHashMap();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet();
    }

    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList();
    }

    public static <k, v> TreeMap<k, v> newTreeMap() {
        return new TreeMap();
    }

    public static <E> TreeSet<E> newTreeSet() {
        return new TreeSet();
    }

    public static <E> Vector<E> newVector() {
        return new Vector();
    }

    public static <k, v> WeakHashMap<k, v> newWeakHashMap() {
        return new WeakHashMap();
    }

    public static <k, v> Map<k, v> newHashMap(k key, v value) {
        Map<k, v> map = newHashMap();
        map.put(key, value);
        return map;
    }

    public static <k, v> ConcurrentHashMap<k, v> newConcurrentHashMap() {
        return new ConcurrentHashMap();
    }
}
