// 그래프 문제
// 코틀린으로 트리 구현 해봤당

import java.io.*;
import java.util.*;

class Node(s: String) {
    var parent: Node? = null
    var left: Node? = null
    var right: Node? = null
    var value = s
}

class Tree(node: Node) {
    var root: Node = node

    fun add(cur: Node, parent: String, left: String, right: String) {
        if (cur.value == parent) {
            if (left != ".") addLeft(cur, Node(left))
            if (right != ".") addRight(cur, Node(right))
        }

        cur.left?.let { add(it, parent, left, right) }
        cur.right?.let { add(it, parent, left, right) }
    }

    private fun addLeft(parent: Node, child: Node) {
        parent.left = child
        child.parent = parent
    }

    private fun addRight(parent: Node, child: Node) {
        parent.right = child
        child.parent = parent
    }

    fun preorder(cur: Node) {
        print(cur.value)

        cur.left?.let { preorder(it) }
        cur.right?.let { preorder(it) }
    }

    fun inorder(cur: Node) {
        cur.left?.let { inorder(it) }
        print(cur.value)
        cur.right?.let { inorder(it) }
    }

    fun postorder(cur: Node) {
        cur.left?.let { postorder(it) }
        cur.right?.let { postorder(it) }
        print(cur.value)
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val N = br.readLine().toInt()

    val tree = Tree(Node("A"))

    for (i in 0 until N) {
        val st = StringTokenizer(br.readLine(), " ")

        val parent = st.nextToken()
        val left = st.nextToken()
        val right = st.nextToken()

        tree.add(tree.root, parent, left, right)
    }

    tree.preorder(tree.root)
    println()
    tree.inorder(tree.root)
    println()
    tree.postorder(tree.root)
}