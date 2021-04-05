package com.tom.oj;




import com.tom.util.ListNode;
import java.util.*;

/**
 * @author WangTao
 */
public class TreeSolution {

    public  TreeNode pre = null;


    private int curVal=0;
    private int curMaxDepth = -1;
//    中序遍历
//    @link https://leetcode-cn.com/problems/find-bottom-left-tree-value/
    public int findBottomLeftValue(TreeNode root) {
        help(root,0);
        return curVal;
    }

    private void help(TreeNode root,int depth){
        if(root==null){
            return;
        }
        if(depth>curMaxDepth){
            curMaxDepth=depth;
            curVal=root.val;
        }
        help(root.left,depth+1);
        help(root.right,depth+1);
    }


    public boolean isValidBST(TreeNode root) {
        //中序遍历为有序序列
        if(root == null){
            return true;
        }
        if(!isValidBST(root.left)){
            return false;
        }
        if (pre != null && pre.val >= root.val){
            return false;
        }
        //更新索引
        pre = root;
        if(!isValidBST(root.right)){
            return false;
        }
        return true;
    }


    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = getLeafNode(root1);
        List<Integer> list2 = getLeafNode(root2);
        int len1 = list1.size();
        int len2 = list2.size();
        if(len1 != len2 ){
            return false;
        }
        for (int i = 0; i < len1; i++) {
            if(!list1.get(i).equals(list2.get(i))){
                return false;
            }
        }
        return true;

    }


    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        int sum = 0;
        if(root.left != null && root.left.left == null && root.left.right == null){
            sum += root.left.val;
        }
        return sumOfLeftLeaves(root.right) + sumOfLeftLeaves(root.left) + sum;
    }


    private List<Integer> getLeafNode(TreeNode root){
        List<Integer> result = new ArrayList<>();
        getNode(root,result);
        return result;

    }

    private void getNode(TreeNode root,List<Integer> tmp){
        if(root == null){
            return;
        }
        if(root.left != null){
            getNode(root.left,tmp);
        }
        if(root.left == null && root.right == null){
            tmp.add(root.val);
        }
        if(root.right != null){
            getNode(root.right,tmp);
        }
    }




    public int minDepth(TreeNode root) {

        int depth = 0;
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            depth = 1;
        }else if(root.left != null && root.right != null){
            depth = 1+Math.min(minDepth(root.left) , minDepth(root.right)) ;
        }else {
            if (root.left != null){
                depth = 1 + minDepth(root.left);
            }
            if (root.right != null){
                depth = 1 + minDepth(root.right);
            }
        }
        return depth;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode node = root;
        Queue<TreeNode> queue = new ArrayDeque<>(10);
        queue.offer(node);
        List<Double> list = new ArrayList<>();
        while (!queue.isEmpty()){
            double sum = 0.0;
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node1 = queue.poll();
                if (node1.left != null){
                    queue.offer(node1.left);
                }
                if (node1.right != null){
                    queue.offer(node1.right);
                }
                sum += node1.val;
            }
            list.add(sum /size);

        }
        return list;
    }

    //给定一个二叉树，检查它是否是镜像对称的。leetcode 101
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null){
                continue;
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            if (t1.val != t2.val) {
                return false;
            }
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        return isSymmetric2(root,root);
    }

    public boolean isSymmetric2(TreeNode t1,TreeNode t2) {
        if (t1 == null && t2 == null){
            return true;
        }
        if (t1 == null || t2 == null){
            return false;
        }
        return t1.val == t2.val && isSymmetric2(t1.left,t2.right)
                && isSymmetric2(t2.right,t1.left);
    }


    //判断是不是平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        if(Math.abs(getHigh(root.left)-getHigh(root.right)) <= 1){
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;

    }

    private int getHigh(TreeNode root){
        if(root==null) {
            return 0;
        }
        return Math.max(getHigh(root.left),getHigh(root.right))+1;
    }

    public int[] levelOrder(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        Queue<TreeNode> queue = new ArrayDeque<>(16);
        List<Integer> list = new ArrayList<>(16);
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            list.add(tmp.val);
            if (tmp.left != null){
                queue.offer(tmp.left);
            }
            if (tmp.right != null){
                queue.offer(tmp.right);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        if(tree == null){
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<ListNode> listNodes = new ArrayList<>();
        queue.offer(tree);
        while (!queue.isEmpty()){
            ListNode head = new ListNode(-1);
            ListNode rear = head;
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                ListNode tmp = new ListNode(node.val);
                rear.next = tmp;
                rear = rear.next;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            listNodes.add(head.next);
        }
        int size = listNodes.size();
        ListNode[] result = new ListNode[size];
        for (int i = 0; i < size; i++) {
            result[i] = listNodes.get(i);
        }
        return result;
    }


    public List<List<Integer>> levelOrderBy(TreeNode root) {
        List<List<Integer>> mylist = new ArrayList<>();
        if(root == null){
            return mylist;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
//            之字型打印
            if(mylist.size() % 2 == 1) {
                Collections.reverse(tmp);
            }
            mylist.add(tmp);
        }
        return mylist;
    }





    public int sumNumbers(TreeNode root) {
        List<List<Integer>> all = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        getAllLeafToRoot(root,tmp,all);
        int sum = 0;
        System.out.println(all);
        for (List<Integer> list:all){
            int tmpVal = 0;
            for(int t:list){
                tmpVal = tmpVal * 10 + t;
            }
            sum += tmpVal;
        }
        return sum;
    }

    private void  getAllLeafToRoot(TreeNode root,List<Integer> tmp,
                                   List<List<Integer>> all){
        if(root == null){
            return ;
        }
        boolean isLeaf = root.left == null && root.right == null;
        tmp.add(root.val);
        if (isLeaf){
            all.add(new ArrayList<>(tmp));
        }
        if(root.left != null){
            getAllLeafToRoot(root.left,tmp,all);
        }
        if(root.right != null){
            getAllLeafToRoot(root.right,tmp,all);
        }
        tmp.remove(tmp.size()-1);
    }


    public int sumNumbers2(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }


    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null){
            return false;
        }
        if (postorder.length == 1){
            return true;
        }
        return veryfiySquenceofBST(postorder,0,postorder.length-1);
    }

    private boolean veryfiySquenceofBST(int[] arr,int start,int end){
        if(start >= end){
            return true;
        }
        int i = start;
        //找到比根节点大的数 后序遍历的特性 最后一个值为根节点 结合 二分搜索树的特点
        //左子树全部小于
        while (arr[i] < arr[end]){
            i++;
        }
        for (int j = i; j < end; j++) {
            //右子树全部大于根节点
            if(arr[j] < arr[end]){
                return false;
            }
        }
        //递归调用左右子树
        return veryfiySquenceofBST(arr,start,i-1) && veryfiySquenceofBST(arr,i,end-1);

    }



    public TreeNode mirrorTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(0,tmp);
        }
        return result;

    }

    public int sumRootToLeaf(TreeNode root) {
        return sumLeaf(root,0);
    }

    private int sumLeaf(TreeNode node,int sum){
        if(node == null){
            return 0;
        }
        sum = sum * 2 + node.val;
        //返回叶子节点 元素值
        if(node.left == null && node.right == null){
            return sum;
        }
        return sumLeaf(node.left,sum) + sumLeaf(node.right,sum);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        return getPath(root,0,sum);

    }

    private boolean getPath(TreeNode node,int currentSum,int expectSum){
        if (node == null) {
            return false;
        }
        boolean isLeaf = node.left == null && node.right == null;
        currentSum += node.val;
        if (currentSum == expectSum && isLeaf){
            return true;
        }
        return   getPath(node.left,currentSum,expectSum)
        || getPath(node.right,currentSum,expectSum);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        int currentSum=0;
        List<Integer> list = new ArrayList<>();
        getPath(root,result,list,currentSum,sum);
        return result;
    }

    private void getPath(TreeNode root,List<List<Integer>> result,
                         List<Integer> tmp,
                         int currentSum,int expectSum){
        boolean isLeaf = root.left == null && root.right == null;
        currentSum += root.val;
        tmp.add(root.val);
        if (currentSum == expectSum && isLeaf){
            result.add(new ArrayList<>(tmp));
        }
        if (root.left != null){
            getPath(root.left,result,tmp,currentSum,expectSum);
        }
        if (root.right != null){
            getPath(root.right,result,tmp,currentSum,expectSum);
        }
        //回溯删除最后一个元素
        tmp.remove(tmp.size()-1);
    }

    private int ret = Integer.MIN_VALUE;


    /**
     对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
     1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
     2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径

     1.只有当前节点
     2.当前节点+左子树
     3.当前节点+右子书
     4.当前节点+左右子树
     **/
    public int maxPathSum(TreeNode root) {
        getMax(root);
        return ret;
    }

    private int getMax(TreeNode r) {
        if(r == null) {
            return 0;
        }
        //判断是否加入 左子树 如果大于0就加入该子树
        int left = Math.max(0, getMax(r.left)); // 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(r.right));
        // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        ret = Math.max(ret, r.val + left + right);
        return Math.max(left, right) + r.val;
    }



//    将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {

        return sortArray(nums,0,nums.length-1);

    }

    private TreeNode sortArray(int[] nums,int left,int right){
        if(left > right){
            return null;
        }
        int mid = (right - left) / 2 + left;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortArray(nums,left,mid-1);
        root.right = sortArray(nums,mid+1,right);
        return root;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        treePath(root,ret,tmp);
        return ret;
    }

    private void treePath(TreeNode root,List<String> reuslt,
                          List<Integer>  tmp){
        if(root == null){
            return;
        }
        tmp.add(root.val);
        if(root.left == null && root.right == null){
            StringBuilder sb = new StringBuilder();
            int len = tmp.size();
            for (int i = 0; i < len; i++) {
                sb.append(tmp.get(i));
                if(i != len -1){
                    sb.append("->");
                }
            }
            reuslt.add(sb.toString());
        }
        if(root.left != null){
            treePath(root.left,reuslt,tmp);
        }
        if(root.right != null){
            treePath(root.right,reuslt,tmp);
        }
        tmp.remove(tmp.size()-1);
    }


    private TreeNode prev;
    private TreeNode s;
    private TreeNode t;

    //@link https://leetcode-cn.com/problems/recover-binary-search-tree/
    public void recoverTree(TreeNode root) {
        recover(root);
        swap(s,t);
    }

    private void recover(TreeNode root){
        if(root == null){
            return ;
        }
        recover(root.left);
        if(prev != null && root.val < prev.val){
            s = (s == null) ? prev : s;
            t = root;
        }
        //暂存
        prev = root;
        recover(root.right);
    }

    private void swap(TreeNode t1,TreeNode t2){
        int tmp = t1.val;
        t1.val = t2.val;
        t2.val = tmp;
    }


    int tilt=0;
    public int findTilt(TreeNode root) {
        traverse(root);
        return tilt;
    }

    public int traverse(TreeNode root) {
        if( root==null ) {
            return 0;
        }
        int left=traverse(root.left);
        int right=traverse(root.right);
        tilt+=Math.abs(left-right);
        return left+right+root.val;
    }


    public TreeNode increasingBST(TreeNode root) {
        List<Integer> node = new ArrayList<>();
        getAllTreeNode(root,node);
        TreeNode result = new TreeNode(-1);
        TreeNode last = result;
        int len = node.size();
        for (int i = 0; i < len ; i++) {
            TreeNode tmp = new TreeNode(node.get(i));
            last.right = tmp;
            last = last.right;
        }
        return result.right;
    }

    private void  getAllTreeNode(TreeNode node,List<Integer> list){
        if (node != null){
            if (node.left != null) {
                getAllTreeNode(node.left, list);
            }
            list.add(node.val);
            if (node.right != null) {
                getAllTreeNode(node.right, list);
            }
        }
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int count = 1;
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                list.add(tmp.val);
                if (tmp.left != null){
                    queue.offer(tmp.left);
                }
                if (tmp.right != null){
                    queue.offer(tmp.right);
                }
            }
            if (count % 2 == 0){
                Collections.reverse(list);
            }
            count++;
            result.add(list);
        }
        return result;
    }




    public int maxLevelSum(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null){
            return 0;
        }
        int index = 1;
        int tmp = 1;
        int min = Integer.MIN_VALUE;
        queue.offer(root);
        while (!queue.isEmpty()){
            int sum = 0;
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmpNode = queue.poll();
                if (tmpNode.left != null){
                    queue.offer(tmpNode.left);
                }
                if (tmpNode.right != null){
                    queue.offer(tmpNode.right);
                }
                sum += tmpNode.val;
            }
            if (sum > min){
                min = sum;
                tmp = index;
            }
            index ++;
        }
        return tmp;
    }

    public int deepestLeavesSum(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()){
            sum = 0;
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmpNode = queue.poll();
                assert tmpNode != null;
                System.out.print(tmpNode.val + "--->");
                if (tmpNode.left != null){
                    queue.offer(tmpNode.left);
                }
                if (tmpNode.right != null){
                    queue.offer(tmpNode.right);
                }
                sum += tmpNode.val;
            }
            System.out.println();
        }
        return sum;

    }



    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
