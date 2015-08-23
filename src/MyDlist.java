
import java.io.*;

/**
 *
 * @author aakash patel
 */

class MyDlist extends DList {

	public MyDlist() {
		super();
	}

	public MyDlist(String f) {
		super();

		String[] result = f.split("\\s");

		addToList(result);
	}

	private void addToList(String[] result) {
		DNode node = new DNode(result[0], null, null);
		this.addFirst(node);

		for (int i = 1; i < result.length; i++) {
			DNode nextNode = new DNode(result[i], node, null);
			this.addAfter(node, nextNode);
			node = nextNode;
		}
	}

	public void printList() {
		System.out.println(this.toString());
	}

	public void newPrintList() {
		String s = this.toString();
		String[] result = s.split(",");
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

	public static MyDlist cloneList(MyDlist originalList) {
		String[] str = new String[originalList.size];

		DNode orgNode = originalList.getFirst();
		str[0] = orgNode.getElement();

		for (int i = 1; i < originalList.size; i++) {
			orgNode = orgNode.getNext();
			str[i] = orgNode.getElement();
		}

		MyDlist newList = new MyDlist();
		newList.addToList(str);
		return newList;
	}

	public void union(MyDlist firstList, MyDlist secList) {

		MyDlist mergeList = MyDlist.cloneList(firstList);
		DNode nFirstNode = secList.getFirst();
		DNode mergeLast = mergeList.getLast();
		boolean flag;

		for (int i = 0; i < secList.size; i++) {

			DNode mNode = firstList.getFirst(); //data
			flag = true; //flag=true

			for (int j = 0; j < firstList.size; j++) {

				if (mNode.getElement().equalsIgnoreCase(nFirstNode.getElement())) {//data == hello

					flag = false;

				}

				mNode = firstList.getNext(mNode);//structures

			}
			if (flag) {
				//insert
				 DNode nextNode = new DNode(nFirstNode.getElement(), mergeLast,
				 null);
				 mergeList.addAfter(mergeLast, nextNode);
				 
				 mergeLast = nextNode;
			}
			nFirstNode = secList.getNext(nFirstNode);
			
		}

		mergeList.printList();

	}

	public void intersection(MyDlist firstList, MyDlist secList) {

		MyDlist intsecList = new MyDlist();
		DNode nFirstNode = secList.getFirst();
		DNode intsecLastNode = null;
		
		boolean flag;

		for (int i = 0; i < secList.size; i++) {

			DNode mNode = firstList.getFirst(); //data
			flag = false;

			for (int j = 0; j < firstList.size; j++) {

				if (mNode.getElement().equalsIgnoreCase(nFirstNode.getElement())) {//data == hello

					flag = true;

				}

				mNode = firstList.getNext(mNode);//structures

			}
			if (flag) {
				//insert
				 DNode nextNode = new DNode(nFirstNode.getElement(), intsecLastNode,
				 null);
				 intsecList.addLast(nextNode);
				 //intsecList.addAfter(intsecLastNode, nextNode);
				 
				 intsecLastNode = nextNode;
			}
			nFirstNode = secList.getNext(nFirstNode);
			
		}

		intsecList.printList();

	}

	public static void main(String args[]) throws Exception {
		// Read File
		FileReader fileReader = new FileReader("C:\\Users\\aakash patel\\workspace\\Dnode\\src\\StringFile.txt");
		BufferedReader bufferRdr = new BufferedReader(fileReader);
		String wholeFileStr = "";
		String lineStr;
		while ((lineStr = bufferRdr.readLine()) != null) {
			wholeFileStr += lineStr + ' ';
		}

		fileReader.close();
		// Example Empty List
		MyDlist emptyList = new MyDlist();
		System.out.println("----------------------Empty list----------------------");
		 emptyList.printList();
		 System.out.println();
		 
		// Example with File List
		 System.out.println("----------------------Imported File list----------------");
		MyDlist fileList = new MyDlist(wholeFileStr);
		 fileList.newPrintList();
		 System.out.println();
		MyDlist secondList = new MyDlist("good morning is hello world");
		//secondList.printList();
		System.out.println("----------------------Union----------------------");
		MyDlist m = new MyDlist();
		m.union(fileList, secondList);
		 System.out.println();
		System.out.println("----------------------Intersection----------------");
		MyDlist insect=new MyDlist();
		insect.intersection(fileList, secondList);
		System.out.println();
		
		
		// secondList.printList();
		// Example print as original
		// fileList.newPrintList();

		// Clone List
		MyDlist cloneList = MyDlist.cloneList(fileList);
		// cloneList.printList();

	}

}
