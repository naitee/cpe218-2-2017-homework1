import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Stack;
import java.util.Scanner;
public class Homework1 {
	public static Node ChristmasTree;
	static Stack<Character> StackToTree = new Stack<Character>();
	public static ByteArrayOutputStream baos = new ByteArrayOutputStream();
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String problem = input.nextLine();
		for (int i = 0; i < problem.length(); i++) {
			StackToTree.add(problem.charAt(i));
		}
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		ChristmasTree = new Node(StackToTree.pop());
		Infix(ChristmasTree);
		inorder(ChristmasTree);
		System.out.print("=");
		System.out.print(Calculate(ChristmasTree));
		TreeGUI.main(args);
	}

	public static void inorder(Node TreeToString) {

		if (TreeToString.operate == '-' || TreeToString.operate == '/' || TreeToString.operate == '*' || TreeToString.operate == '+') {
			if (TreeToString != ChristmasTree) System.out.print("(");
			inorder(TreeToString.left);
			System.out.print(TreeToString.operate);
			inorder(TreeToString.right);
			if (TreeToString != ChristmasTree) System.out.print(")");
		} else {
			if (TreeToString != ChristmasTree) System.out.print(TreeToString.operate);
		}
	}

	public static void Infix(Node node) {
		if (node.operate == '+' || node.operate == '-' || node.operate == '*' || node.operate == '/') {
			node.right = new Node(StackToTree.pop());
			Infix(node.right);
			node.left = new Node(StackToTree.pop());
			Infix(node.left);
		}
	}

	public static int Calculate(Node node) {
		if (null != node.operate) switch (node.operate) {
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


	static class Node {
		Node left;
		Node right;
		Character operate;

		Node(char n) {
			operate = n;
		}

		public String toString() {
			return operate.toString();
		}
	}
}