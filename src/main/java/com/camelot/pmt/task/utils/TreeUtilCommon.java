package com.camelot.pmt.task.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TreeUtilCommon {

    /**
     * 组装实体树
     * 
     * @param nodes
     *            要组装成树的实体List
     * @param className
     *            实体全类名
     * @param idName
     *            id字段名 类型为基本类型，自动封装类型（如Long、Integer），String
     * @param pidName
     *            父id字段名 类型为基本类型，自动封装类型（如Long、Integer），String
     * @param childrenListName
     *            子节点存放List的字段名
     * @return
     * @throws Exception
     * @author nijianchun
     * @since 2016年11月9日 下午3:42:57
     */
    public static <T> List<T> buildTree(List<T> nodes, String className, String idName, String pidName,
            String childrenListName) throws Exception {
        List<T> tree = new ArrayList<T>();// 树
        for (T node : nodes) {// 遍历
            tree = findChildren(node, tree, className, idName, pidName, childrenListName);// 从树上找到node的孩子节点并返回一颗新树
            boolean isChild = isChild(node, tree, className, idName, pidName, childrenListName);// 从树上找到node的父节点并组装，如果没有父节点，则返回false
            if (!isChild) {// 树上没有父节点，把node挂到树上
                tree.add(node);
            }
        }
        return tree;
    }

    /**
     * 根据ID从实体树上获取节点
     * 
     * @param tree
     *            实体树
     * @param id
     *            id值
     * @param className
     *            实体全类名
     * @param idName
     *            id字段名 类型为基本类型，自动封装类型（如Long、Integer），String
     * @param pidName
     *            父id字段名 类型为基本类型，自动封装类型（如Long、Integer），String
     * @param childrenListName
     *            子节点存放List的字段名
     * @return 实体节点
     * @throws Exception
     * @author nijianchun
     * @since 2016年11月9日 下午3:47:25
     */
    public static <T> T getNodeById(List<T> tree, Object id, String className, String idName, String pidName,
            String childrenListName) throws Exception {
        if (tree == null || tree.size() == 0) {
            return null;
        }
        for (T node : tree) {
            Class c = Class.forName(className);
            Method getId = c.getMethod("get" + upFirst(idName));// 获取id的get方法
            Object node_id = getId.invoke(node);// 调用id的get方法获取node的id值

            // TODO 先不处理类型的问题，直接用简单类型、封装类型、String判断
            if (node_id.toString().equals(id.toString())) {
                return node;
            } else {
                Method getChildrenList = c.getMethod("get" + upFirst(childrenListName));// 获取childrenList的get方法
                List childrenList = (List) getChildrenList.invoke(node);// 调用childrenList的get方法获取node的childrenList值
                T n = (T) getNodeById(childrenList, id, className, idName, pidName, childrenListName);
                if (n != null) {
                    return n;
                }
            }
        }
        return null;
    }

    // node是n的父节点
    private static <T> boolean isParent(T n, T node, String className, String idName, String pidName,
            String childrenListName) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {// node是n的父节点
        Class c = Class.forName(className);
        Method getId = c.getMethod("get" + upFirst(idName));// 获取id的get方法
        Method getPid = c.getMethod("get" + upFirst(pidName));// 获取pid的get方法
        Object node_id = getId.invoke(node);// 调用id的get方法获取node的id值
        Object n_pid = getPid.invoke(n);// 调用pid的get方法获取n的pid值
        // 根据值的类型比较是否相同
        // TODO 先不处理类型的问题，直接用简单类型、封装类型、String判断
        if (n_pid != null && n_pid.toString().equals(node_id.toString())) {
            return true;
        }
        return false;
    }

    private static <T> boolean isChild(T node, List<T> tree, String className, String idName, String pidName,
            String childrenListName) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class c = Class.forName(className);
        Method getPid = c.getMethod("get" + upFirst(pidName));// 获取pid的get方法
        Object node_pid = getPid.invoke(node);// 调用pid的get方法获取node的pid值
        if (node_pid == null) {
            return false;
        }
        if (tree == null || tree.size() == 0) {// 要遍历的list长度为0
            return false;
        }
        for (T n : tree) {
            Method getChildrenList = c.getMethod("get" + upFirst(childrenListName));// 获取childrenList的get方法
            List childrenList = (List) getChildrenList.invoke(n);// 调用childrenList的get方法获取node的childrenList值
            if (isParent(node, n, className, idName, pidName, childrenListName)) {// n是否是node的父亲
                if (childrenList == null) {
                    Method setChildrenList = c.getMethod("set" + upFirst(childrenListName), List.class);// 获取childrenList的set方法
                    setChildrenList.invoke(n, new ArrayList<T>());
                }
                childrenList = (List) getChildrenList.invoke(n);// 调用childrenList的get方法获取node的childrenList值
                childrenList.add(node);
                return true;
            } else {// 不是，则遍历n的子节点
                if (isChild(node, childrenList, className, idName, pidName, childrenListName)) {
                    return true;
                }
            }
        }
        return false;
    }

    // tree的部分根节点是node的子节点
    private static <T> List<T> findChildren(T node, List<T> tree, String className, String idName, String pidName,
            String childrenListName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException, ClassNotFoundException {
        List<T> newTree = new ArrayList<T>();// 新树
        for (T n : tree) {
            if (isParent(n, node, className, idName, pidName, childrenListName)) {
                Class c = Class.forName(className);
                Method getChildrenList = c.getMethod("get" + upFirst(childrenListName));// 获取childrenList的get方法
                List childrenList = (List) getChildrenList.invoke(node);// 调用childrenList的get方法获取node的childrenList值
                if (childrenList == null) {
                    Method setChildrenList = c.getMethod("set" + upFirst(childrenListName), List.class);// 获取childrenList的set方法
                    setChildrenList.invoke(node, new ArrayList<T>());
                }
                childrenList = (List) getChildrenList.invoke(node);
                childrenList.add(n);
            } else {// 不是孩子
                newTree.add(n);
            }
        }
        return newTree;
    }

    private static String upFirst(String name) {// 首字母大写
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

}