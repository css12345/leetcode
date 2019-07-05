package pers.cs.day16;

public class Hanoi {

	public static void main(String[] args) {
		Hanoi hanoi = new Hanoi();
		hanoi.hanoi(1);
		System.out.println();
		hanoi.hanoi(3);
	}
	
	public void hanoi(int n) {
		if(n <= 0)
			return;
		move("left","right","mid",n);
	}

	private void move(String from, String to, String help, int n) {
		if(n == 1)
			System.out.println("move from " + from + " to " + to);
		else {
			move(from, help, to, n - 1);
			System.out.println("move from " + from + " to " + to);
			move(help, to, from, n - 1);
		}
	}

}
