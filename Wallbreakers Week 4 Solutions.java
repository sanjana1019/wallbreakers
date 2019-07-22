//Solutions to week 4 problems


//--------------Linked Lists --------------//

//--------------------------------- Reverse Linked List ------------------------------//

class Solution {
    public ListNode reverseList(ListNode head) {
          //edge cases
		  //if empty head 
		  if (head == null){
			  return null; 
		  }
		  //if only 1 node
		  if (head.next == null){
			  return head; 
		  }
 		
		  //create new node for head of list 
		  ListNode n = new ListNode(head.val); 
		  //hd will be head of this new list. currently points at n.   
		  ListNode hd = n; 
		  //traverse the linked list. start at 2nd node in input. 
		  ListNode tmp = head.next; 
		  while (tmp != null){
			  //get the val in tmp and create new node out of it 
			  ListNode newNode = new ListNode(tmp.val); 
			  //have tempN hold the prev head
			  ListNode tempN = hd;    
			  //now move hd to newNode
			  hd = newNode;       
			  //set hd's next to be tempN
			  hd.next = tempN; 
			  tmp = tmp.next; 
		  }
		  
		 //return head of newly formed list 
		return hd;	
    }
}


//--------------------------------- Odd Even Linked List ------------------------------//
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        		  //if empty head 
		  if (head == null){
			  return null; 
		  }
		  //if only 1 node
		  if (head.next == null){
			  return head; 
		  }
		  //if only 2 nodes
		  if (head.next.next == null){
			  return head; 
		  }
		  //must have at least 3 nodes 
		  //the first node is odd, create a head to hold odd linked list
		  ListNode headOdd  = new ListNode(head.val); 
		  //tmp pointer to this node 
		  ListNode tmpOdd = headOdd; 
		  
		  //the 2nd node is even, create a head to hold even linked list
		  ListNode headEven  = new ListNode(head.next.val); 
		  //tmp pointer to this node 
		  ListNode tmpEven = headEven; 
		  
		  //loop through the rest of the nodes in head 
		  ListNode tmp = head.next.next; 
		  int i = 3; 
		  while (tmp != null){
			  //create new node 
			  ListNode newNode  = new ListNode(tmp.val); 
			  if (i % 2 == 0){
				  //index is even 
				  tmpEven.next = newNode; 
				  //move tmp
				  tmpEven = newNode; 
			  } else {
				 //index is odd 
				  tmpOdd.next = newNode;
				//move tmp
				  tmpOdd = newNode;
			  }		  
			  tmp = tmp.next; 
			  i++; 
		  }
		  	  
		  
		  //tmpOdd should now point to its last node 
		  //join both lists 
		  tmpOdd.next = headEven; 
		 
		return headOdd;
    }
}
//--------------------------------- Reverse Nodes in K Group ------------------------------//

//--------------------------------- lru cache ------------------------------//
public class LRUCache {

	LinkedList<Integer> list; 
	int cap; 
	HashMap<Integer, Integer> hm; 
	
	
	   public LRUCache(int capacity) {
		   //will keep track of recently used numbers 
		   list = new LinkedList<Integer>(); 
		   cap = capacity; 
		   //hashmap will keep track of pairings 
		   hm = new HashMap<Integer, Integer>(); 
	        
	    }
	    
	   	//Get the value (will always be positive)
	   	//of the key if the key exists in the cache, otherwise return -1.
	    public int get(int key) {
	    	if (hm.containsKey(key)){
	    		//update linked list 
	    		//remove this key from list, then add it at the end of list 
	        	list.remove(list.indexOf(key)); 
	        	list.addLast(key);
	    		return hm.get(key); 
	    	} else {
	    		return -1; 
	    	}	        
	    }
	    
	    //Set or insert the value if the key is not already present. When 
	    //the cache reached its capacity, 
	    //it should invalidate the least recently used 
	    //item before inserting a new item.
	    public void put(int key, int value) {
	    	if(list.size() == cap && !hm.containsKey(key)){
	    		//if list size is at max capacity, then remove lru item 
	    		//remove from head of list
	    		//remove this num from map
	    		hm.remove(list.removeFirst());     		
	    	}
	    	//now we can add the new key	    	
	        if(!hm.containsKey(key)){
	        	//if hm doesn't currently contain this key, then add it
	        	hm.put(key, value); 
	        	//also add it to the end of the list
	        	list.add(key); 	  
	        } else {
	        	//hm already has this key, so update hm? 
	        	hm.put(key, value); 
	        	//remove this key from list, then add it at the end of list 
	        	list.remove(list.indexOf(key));  
	        	list.addLast(key);
	        }
	    }
	    
		    
}









//---------------Stacks--------------------//

//---------------------------- Baseball Game ------------------------------//

class Solution {
    public int calPoints(String[] ops) {
        
   		 //stack will hold all the valid integers
		 Stack<Integer> stack = new Stack<Integer>(); 
		 
		 //loop through ops
		 for (int i = 0; i < ops.length; i++){
			 if (ops[i].equals("+")){
				 int n1 = stack.pop(); 
				 int n2 = stack.peek(); 
				 int sum = n1 + n2; 
				 //put back into stack 
				 stack.push(n1); 
				 stack.push(sum); 				 
			 } else if (ops[i].equals("D")){
				 int n1 = stack.peek(); 
				 //double it
				 int doubleN = n1*2; 
				 stack.push(doubleN);				 
			 } else if (ops[i].equals("C")){
				 //delete the last score
				 stack.pop(); 
			 } else {
				 //must be an integer. convert string to integer and push
				 stack.push(Integer.parseInt(ops[i])); 					
			 }
		 }
		 
		 int sum = 0; 
		 //pop and add up everything in the stack
		 for (int k = 0; k < stack.size(); k++){
			 sum += stack.get(k); 
		 }
		 
		return sum; 

    }
}


//---------------------------- Next Greater Element i ------------------------------//
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    
        
		//create a stack
		Stack<Integer> stack = new Stack<Integer>(); 
		//create hashmap
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>(); 
		//create array to hold solution
		int[] arr = new int[nums1.length]; 

		//edge case
		if(nums2.length == 0 && nums1.length == 0){
			return arr; 
		}

		//add first elem in nums2 to stack
		stack.push(nums2[0]);

		//iterate through nums2
		for (int i = 1; i < nums2.length; i++){
			//System.out.println(i);
			while (!stack.isEmpty() && nums2[i] > stack.peek()){
				//pop off top elememt
				int popped = stack.pop(); 
				//put in hm
				hm.put(popped, nums2[i]);
			} 
			stack.push(nums2[i]); 
		}

		//iterate through nums1
		for (int i = 0; i < nums1.length; i++){
			if (hm.containsKey(nums1[i])){
				int greater = hm.get(nums1[i]); 
				arr[i] = greater; 
			} else {
				//hm not have greater num. put -1
				arr[i] = -1; 
			}
		}

		return arr;
    }
}
//---------------------------- Valid Parenthesis ------------------------------//

class Solution {
    public boolean isValid(String input) {
    	String openers = "([{";
		String closers = ")]}";
		boolean flag = true; 

		// Get character
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < input.length(); i++){
			char current = input.charAt(i);

			// returns -1 if the 'c' char is not in the openers String
			int openPos = openers.indexOf(current); 
			if(openPos == -1) {
				//not an opener 
				int closePos = closers.indexOf(current);
				//not a closer either 
				if (closePos == -1) {
					// Skip character
					continue;
				} else {
					// We have a closer 
					if (stack.isEmpty()){
						flag = false; 
						break; 
					}
					char prev = stack.pop(); // Pop what should be an opener
					int popOpenPos = openers.indexOf(prev);
					if (popOpenPos == closePos) {
						// they match! is balanced
						// continue
					} else {
						//don't match
						flag = false; 
						break; 
					}
				}
			} else{
				// We have an opener, push it onto the stack
				stack.push(current);
			}
		}
		if(stack.size() > 0){
			//we have an unmatched symbol left
			flag = false; 
		}
		return flag; 
        
        
    }
        		
}







//----------------- Queues ----------------------//


//------------------------ Implement Stacks using Queues ----------------------------//
public class MyStack {
	
	Queue<Integer> q;
	Queue<Integer> q2; 
	
    /** Initialize your data structure here. */
    public MyStack() {
    	//q will simulate the stack
    	q = new LinkedList<>();
        //2nd queue
    	q2 = new LinkedList<>(); 
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
    	//add to the queue
        q.add(x); 
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
    	while (q.size() > 1){
    	 	//add to q2 what is popped from q1
        	q2.add(q.poll()); 
    	}
    	//size should be 1
    	int polled = q.poll();   	
    	//copy all from q2 back into q1
    	while (!q2.isEmpty()){
    		q.add(q2.poll()); 
    	}    	
		return polled;    	
    }
    
    /** Get the top element. */
    public int top() { 
    	while (q.size() > 1){
    	 	//add to q2 what is popped from q1
        	q2.add(q.poll()); 
    	}  
    	//size should be 1 now
    	int peeked = q.peek();
    	//add the top num to q2
    	q2.add(q.poll());    	
    	//copy all from q2 back into q1
    	while (!q2.isEmpty()){
    		q.add(q2.poll()); 
    	}
		return peeked;        
    }
        
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty(); 
    }
}


//---------------------------- Implement Queues using Stacks ------------------------------//
class MyQueue {

	Stack<Integer> stack1;  
	Stack<Integer> stack2;  
	
    /** Initialize your data structure here. */
    public MyQueue() {
    	stack1 = new Stack<Integer>(); 
    	stack2 = new Stack<Integer>();        
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.add(x); 
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
    	while (stack1.size() > 1){
    		//add what is popped to stack 2
    		stack2.push(stack1.pop()); 
    	}
    	//now stack1 size should be 1
    	int popped = stack1.pop();         
    	//transfer all from stack2 back to stack1 
    	while (!stack2.isEmpty()){
    		stack1.push(stack2.pop()); 
    	}
    	return popped; 
    }
    
    /** Get the front element. */
    public int peek() {
       	while (stack1.size() > 1){
    		//add what is popped to stack 2
    		stack2.push(stack1.pop()); 
    	}
    	//now stack1 size should be 1
    	int peeked = stack1.peek(); 
        
    	//transfer all from stack2 back to stack1 
    	while (!stack2.isEmpty()){
    		stack1.push(stack2.pop()); 
    	}
    	return peeked;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty(); 
    }
}


//---------------------------- Rotate Array ------------------------------//
class Solution {
    public void rotate(int[] nums, int k) {
        

        if (nums.length < k){
            k = k % nums.length;
        }
        
        Queue<Integer> q = new LinkedList<>();

		//add elements to queue 
		for (int i = 0; i < nums.length; i++){
			q.add(nums[i]);
		}
		
		//loop through the array starting at kth position
		for (int i = k; i < nums.length; i++){
			//at kth pos, get the num from queue
			nums[i] = q.poll(); 
		}
		
		//if q is not empty
		if(k != 0){
			//then go to beg of the array and put elems there
			for(int j = 0; j < k; j++){
				nums[j] = q.poll(); 
			}
		}
		
    }
}







//----------- Heaps -----------------//

//---------------------------- Top K Frequent Elements ------------------------------//

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        		
		//create hmap
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>(); 
		
		//put all nums in hmap
		for (int i = 0; i < nums.length; i++){
			//if new element
			if(!hm.containsKey(nums[i])){
				hm.put(nums[i], 1); 
			} else {
				//already contains key, so update it
				int count = hm.get(nums[i]); 
				count++; 
				hm.put(nums[i], count); 		
			}
		}
		
		//hm print
		//System.out.println("hm: " + hm);
		
		//create max priority queue		
		PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((entry1,entry2) -> (entry2.getValue() - entry1.getValue()));
	

		//get the entries from hm and put them into max heap 
		for (Map.Entry<Integer, Integer> e: hm.entrySet()){
			maxHeap.add(e); 
		}
		
		//get the k max values from the heap and put key in list
		ArrayList<Integer> al = new ArrayList<Integer>(); 
		for (int j = 0; j < k; j++){
			al.add(maxHeap.poll().getKey()); 
		}

		return al; 
    }
}

//---------------------------- Merge K Sorted Lists ------------------------------//

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
			return null; 
		}
        
        //create min heap 
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); 
		
		//loop through array and get each individual list
		for (int i = 0; i < lists.length; i++){
			    ListNode currList = lists[i]; 
			    //iterate through the nodes and add value to a min heap
			    ListNode tmp = currList; 
			    while (tmp != null){
				    minHeap.add(tmp.val); 
				    tmp = tmp.next; 
			    }
		}
		
        //check      
        if(minHeap.isEmpty()){
            return null; 
        }
		
		//minHeap is built. take out each min node one by one and 
		//add to a newly formed linked list 
		ListNode head = new ListNode(minHeap.poll());
		ListNode tmp = head; 
		//keep going while its not empty
		while(!minHeap.isEmpty()){
			ListNode newNode = new ListNode(minHeap.poll()); 
			tmp.next = newNode; 
			tmp = tmp.next; 
		}
		
		//return head of new linked list
		return head; 
    }
}



//-------Combinatorial generation-------------//

//---------------------------- Subsets ------------------------------//

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
              
		int num = (int) Math.pow(2, nums.length); 

		//will hold all the solutions
		ArrayList<List<Integer>> sol = new ArrayList<List<Integer>>(); 
		
		for (int i = 0; i < num; i++){
			//get binary
			String bin = getBinary(i, nums.length); 
			//create list to hold sol
			ArrayList<Integer> al = new ArrayList<Integer>(); 
			for (int j = 0; j < bin.length(); j++){
				if (bin.charAt(j) == '1'){
					//then include that num in sol
					al.add(nums[j]); 
				}
			}
			//add this list to the sol of solutions
			sol.add(al); 
		}		
		return sol; 
    }
  
       	 
	public static String getBinary(int dec, int k){
		String sol = ""; 
		while (dec > 0){ 
			int remainder = dec % 2;
			dec = dec/2; 
			sol = sol + remainder; 			
		}
		while (sol.length() < k){
			sol = sol + '0'; 
		}
		return sol;		 
	}
    
}




