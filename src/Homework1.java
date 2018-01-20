import java.util.Stack;
public class Homework1 {
		public static Node ChristmasTree ;
		static Stack<Character> StackToTree=new Stack<Character>();

		public static void main(String[] args) {
			String problem = "251-*32*+";
			for(int i=0;i<problem.length();i++){
				StackToTree.add(problem.charAt(i));
			}
			ChristmasTree = new Node(StackToTree.pop());
			Infix(ChristmasTree);
			inorder(ChristmasTree);
			System.out.print("=");
			System.out.print(Calculate(ChristmasTree));
		}

		public static void inorder(Node TreeToString) {

			if(TreeToString.operate == '-'|| TreeToString.operate == '/'|| TreeToString.operate == '*'|| TreeToString.operate == '+'){
				if(TreeToString!=ChristmasTree) System.out.print("(");
				inorder(TreeToString.left);
				System.out.print(TreeToString.operate);
				inorder(TreeToString.right);
				if(TreeToString!=ChristmasTree) System.out.print(")");
			}else{
				if(TreeToString!=ChristmasTree) System.out.print(TreeToString.operate);
			}
		}

		public static void Infix(Node node){
			if(node.operate == '+'|| node.operate == '-'|| node.operate == '*'|| node.operate == '/'){
				node.right = new Node(StackToTree.pop());
				Infix(node.right);
				node.left = new Node(StackToTree.pop());
				Infix(node.left);
			}
		}

		public static int Calculate(Node node){
			if(null != node.operate)switch (node.operate) {
				case '+':
					return Calculate(node.left) + Calculate(node.right);
				case '-':
					return Calculate(node.left) - Calculate(node.right);
				case '*':
					return Calculate(node.left) * Calculate(node.right);
				case '/':
					return Calculate(node.left) / Calculate(node.right);
				default:
					return Integer.parseInt(node.operate.toString());

			}
			return 0;

		}


	static class Node{
		Node left ;
		Node right ;
		Character operate;
		Node(char n)
		{
			operate = n;
		}
	}


}
