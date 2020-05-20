package lab2;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

class Lab2 {
	public static int FCFS() {
		int head;
		int seekTime = 0;
		Random rand = new Random();
		

		System.out.println("Enter starting head location: ");
		Scanner sc = new Scanner(System.in);
		head = sc.nextInt();

		System.out.println("Enter number of disk locations: ");
		int numberOfDiskLocations = sc.nextInt();
		int[] disk_location = new int[numberOfDiskLocations];

		System.out.println("Enter disk range: ");
		int temp = sc.nextInt();
		
		for (int i = 0; i < numberOfDiskLocations; i++) {
			disk_location[i] = rand.nextInt(temp)+1;
		}

		System.out.println("Order \t Seek Time");
		numberOfDiskLocations = head;

		for (int i = 0; i < numberOfDiskLocations; i++) {
			
			System.out.print(disk_location[i] + " \t ");
			numberOfDiskLocations = Math.abs(numberOfDiskLocations - disk_location[i]);
			
			System.out.println(numberOfDiskLocations);
			seekTime += numberOfDiskLocations;
			numberOfDiskLocations = disk_location[i];
		}

		System.out.println();
		System.out.println("Total Seek Time :" + seekTime);
		sc.close();
		return 0;
	}

	public static int SSTF() {
		int head;
		int seekTime = 0;
		Random rand = new Random();

		System.out.println("Enter starting head location: ");
		Scanner sc = new Scanner(System.in);
		head = sc.nextInt();

		System.out.println("Enter number of disk locations: ");
		int numberOfDiskLocations = sc.nextInt();

		int[] disk_location = new int[numberOfDiskLocations];

		System.out.println("Enter disk range: ");
		int temp = sc.nextInt();
		
		for (int i = 0; i < numberOfDiskLocations; i++) {
			disk_location[i] = rand.nextInt(temp)+1;
		}

		int[] visited = new int[numberOfDiskLocations + 1];

		System.out.println("Disk Location \t Seek Time");

		for (int i = 0; i < numberOfDiskLocations; i++) {
			int min = Integer.MAX_VALUE;
			int pos = 0;
			for (int j = 0; j < numberOfDiskLocations; j++) {
				if (Math.abs(disk_location[j] - head) < min) {
					if (visited[j] == 0) {
						min = Math.abs(disk_location[j] - head);
						pos = j;
					}
				}
			}
			visited[pos] = 1;
			seekTime += Math.abs(disk_location[pos] - head);
			System.out.println(disk_location[pos] + "\t\t " + seekTime);
			head = disk_location[pos];
		}

		System.out.println();
		System.out.println("Total Seek Time :" + seekTime);
		sc.close();
		return 0;
	}

	public static int CSCAN() {
		int preHead;
		int head;
		int trailHead;
		int seekTime = 0;
		int f = 0;
		Random rand= new Random();
		
		System.out.println("Enter starting head location: ");
		Scanner sc = new Scanner(System.in);
		head = sc.nextInt();

		System.out.println("Enter previous header location: ");
		preHead = sc.nextInt();

		System.out.println("Enter trail track location: ");
		trailHead = sc.nextInt();

		System.out.println("Enter number of disk locations: ");
		int numberOfDiskLocations = sc.nextInt();
		int[] disk_location = new int[numberOfDiskLocations];

		System.out.println("Enter disk range:  ");
		int temp = sc.nextInt();
		
		for (int i = 0; i < numberOfDiskLocations; i++)
			disk_location[i] = rand.nextInt(temp)+1;

		int[] visited = new int[numberOfDiskLocations + 1];

		System.out.println("Disk Location \t Seek Time");

		if (preHead <= head)
			f = 0;
		else
			f = 1;

		for (int i = 0; i < numberOfDiskLocations; i++) {
			int pos = -1;

			int min = Integer.MAX_VALUE;

			for (int j = 0; j < numberOfDiskLocations; j++) {

				if (f == 0) {
					if (disk_location[j] > head && min > disk_location[j] - head && visited[j] == 0) {
						min = disk_location[j] - head;
						pos = j;
					}
				}

				else if (f == 1) {
					if (disk_location[j] <= head && min > head - disk_location[j] && visited[j] == 0) {
						pos = j;
						min = head - disk_location[j];
					}
				}
			}

			if (pos == -1) {
				if (f == 0) {
					seekTime += Math.abs(trailHead - head);
					System.out.println(trailHead + "\t\t " + seekTime);
					head = 0;
				} else {
					seekTime += Math.abs(0 - head);
					System.out.println(0 + "\t\t " + seekTime);
					head = trailHead;
				}
				System.out.println("----------Changing Directions------------");
				System.out.println();
				System.out.println("Order \t Seek Time");
				i--;
				continue;
			}

			visited[pos] = 1;
			seekTime += Math.abs(disk_location[pos] - head);
			System.out.println(disk_location[pos] + "\t\t " + seekTime);
			head = disk_location[pos];
		}

		System.out.println();
		System.out.println("Total Seek Time :" + seekTime);
		sc.close();
		return 0;
	}

	public static int SCAN() {
		Scanner sc = new Scanner(System.in);
		int head;
		int preHead;
		int trailHead;
		int seekTime = 0;
		int f = 0;
		int numberOfDiskLocations;
		Random rand = new Random();

		System.out.println("Enter starting head location: ");
		head = sc.nextInt();

		System.out.println("Enter previous header location: ");
		preHead = sc.nextInt();

		System.out.println("Enter trail track location: ");
		trailHead = sc.nextInt();

		System.out.println("Enter number of disk locations: ");
		numberOfDiskLocations = sc.nextInt();

		int[] disk_location = new int[numberOfDiskLocations];

		System.out.println("Enter disk range:  ");
		int temp = sc.nextInt();
		
		for (int i = 0; i < numberOfDiskLocations; i++) {
			disk_location[i] = rand.nextInt(temp)+1;
		}

		int[] visited = new int[numberOfDiskLocations + 1];

		System.out.println("Order \t Seek Time");

		if (preHead <= head)
			f = 0;
		else
			f = 1;

		for (int i = 0; i < numberOfDiskLocations; i++) {
			int pos = -1;
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < numberOfDiskLocations; j++) {
				if (f == 0) {
					if (disk_location[j] > head && min > Math.abs(disk_location[j] - head)
							&& visited[j] == 0) {
						min = Math.abs(disk_location[j] - head);
						pos = j;
					}
				}

				else if (f == 1) {
					if (disk_location[j] <= head && min > Math.abs(disk_location[j] - head)
							&& visited[j] == 0) {
						pos = j;
						min = Math.abs(disk_location[j] - head);
					}
				}
			}
			if (pos == -1) {
				if (f == 0) {
					f = 1;
					seekTime += Math.abs(trailHead - head);
					System.out.println(trailHead + "\t\t " + seekTime);
					head = trailHead;
				} else {
					f = 0;
					seekTime += Math.abs(0 - head);
					System.out.println(0 + "\t\t " + seekTime);
					head = 0;
				}
				System.out.println("**Changing Directions**");
				System.out.println();
				System.out.println("Order \t Seek Time");
				i--;
				continue;
			}
			visited[pos] = 1;
			seekTime += Math.abs(disk_location[pos] - head);
			System.out.println(disk_location[pos] + "\t\t " + seekTime);
			head = disk_location[pos];
		}

		System.out.println();
		System.out.println("Total Seek Time :" + seekTime);
		sc.close();
		return 0;
	}

	public static int RealTimeEDF() {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		
		int numberOfDiskLocations;
		int seekTime = 0;
		
		Vector<Integer> deadlines = new Vector<Integer>();
		Vector<Integer> request = new Vector<Integer>();

		System.out.print("Enter the number of requests :");
		numberOfDiskLocations = sc.nextInt();
		
		System.out.println("Enter the range of requests");
		int rRange = sc.nextInt();

		System.out.print("Enter the requests:");
		for (int i = 0, temp; i < numberOfDiskLocations; i++) {
			temp = rand.nextInt(rRange)+1;
			int deadline = rand.nextInt(10);
			request.addElement(temp);
			deadlines.addElement(deadline);
		}

		Collections.sort(deadlines);
		
		System.out.println("Order \t Deadline \t Seek Time ");
		
		for (int i = 0; i < request.size(); i++) {
			System.out.print(request.elementAt(i) + " \t ");
			System.out.print(deadlines.elementAt(i) + " \t \t");
			numberOfDiskLocations = Math.abs(numberOfDiskLocations - request.elementAt(i));
			System.out.println(numberOfDiskLocations);
			seekTime += numberOfDiskLocations;
			numberOfDiskLocations = request.elementAt(i);

		}
		System.out.println();
		System.out.println("Total Seek Time :" + seekTime);
		
		sc.close();
		return 0;
	}

	public static void main(String[] args) {

		System.out.println("Please choose an algorithm!");
		System.out.println("1) FCFS ");
		System.out.println("2) SSTF");
		System.out.println("3) SCAN");
		System.out.println("4) CSCAN");
		System.out.println("5) Real Time EDF");

		Scanner sc = new Scanner(System.in);
		int f;
		f = sc.nextInt();

		switch (f) {
		case 1:
			FCFS();
			break;
		case 2:
			SSTF();
			break;
		case 3:
			SCAN();
			break;
		case 4:
			CSCAN();
			break;
		case 5:
			RealTimeEDF();
		}

		sc.close();
	}
}