//wenlu cheng
// hw#3
//This is extra credit implementation of avl tree
class avl{
    
    Node root;
    private static final int BALANCE = 1; //the difference of subtrees
    
    private class Node{
    	
    	// These attributes of the Node class will not be sufficient for those attempting the AVL extra credit.
    	// You are free to add extra attributes as you see fit, but do not remove attributes given as it will mess with help code.
        String keyword;
        Record record;
        int size;
        Node l;
        Node r;
        int height;

        private Node(String k){
        	// TODO Instantialize a new Node with keyword k.
        	keyword = k;
        	record = null;
        	size = 0;
        	l = null;
        	r = null;
        	height = 0;
        }

        private void update(Record r){
        	//TODO Adds the Record r to the linked list of records. You do not have to check if the record is already in the list.
        	//HINT: Add the Record r to the front of your linked list.
        	if(record == null){
        		record = r;
        	}else{
        		r.next = record;
        		record = r;
        	}    	
        }     
    }

    public avl(){
        this.root = null;
    }
      
    public void insert(String keyword, FileData fd){
        Record recordToAdd = new Record(fd.id, fd.author, fd.title, null);
        //TODO Write a recursive insertion that adds recordToAdd to the list of records for the node associated
        //with keyword. If there is no node, this code should add the node.
        root = insert(keyword,root,recordToAdd);
        root.size++;
    }
    
    private Node insert(String keyword,Node root, Record recordtoadd){
    	if(root == null){
    		Node temp = new Node(keyword);
    		temp.update(recordtoadd);
    		return temp;
    	}
    	
    	int result = keyword.compareTo(root.keyword);
    	
    	if(result < 0 ){
    		root.l = insert(keyword,root.l,recordtoadd);
    	}
    	else if (result > 0){
    		root.r = insert(keyword,root.r,recordtoadd);
    	}
    	else
    		root.update(recordtoadd);
    	
    	//after the insertion, balance the node
    	return balance(root);		
    }
    
    // balance method to check with the AVL property
    private Node balance(Node root){
    	if(root == null)
    		return root;
    	
    	if(height(root.l) - height(root.r) > BALANCE)
    		if(height(root.l.l) >= height(root.r.r))
    			root = rotatewithleft(root);
    		else
    			root = doublewithleft(root);
    	else if(height(root.r) - height(root.l) > BALANCE)
    		if(height(root.r.r) >= height(root.l.l))
    			root = rotatewithright(root);
    		else
    			root = doublewithright(root);
    	
    	root.height = Math.max(height(root.l), height(root.r)) + 1;
    	
    	return root;	
    }
    
    //single rotate for left left case
    private Node rotatewithleft(Node root){
    	//rotation 
    	Node temp = root.l;
    	root.l = temp.r;
    	temp.r = root;
    	//update heights
    	temp.height = Math.max(height(temp.l), height(temp.r)) + 1;
    	root.height = Math.max(height(root.l), height(root.r)) + 1;
    	return temp;
    }
    
    // double rotate for left right case
    private Node doublewithleft(Node root){
    	root.l = rotatewithright(root.l);
    	return rotatewithleft(root);
    }
    
    // single rotate for right right case
    private Node rotatewithright(Node root){
    	Node temp = root.r;
    	root.r = temp.l;
    	temp.l = root;
    	//update heights
    	temp.height = Math.max(height(temp.l), height(temp.r)) + 1;
    	root.height = Math.max(height(root.l), height(root.r)) + 1;
    	return temp;
    }
    
    // double rotate for right left case
    private Node doublewithright(Node root){
    	root.r = rotatewithleft(root.r);
    	return rotatewithright(root);
    }
    
    // return height of the tree
    private int height(Node root){
    	if(root == null)
    		return -1;
    	return root.height;
    }
    
    //returns true if a particular keyword exists in the bst
    public boolean contains(String keyword){
    	return contains(keyword,root);
    }
    
    private boolean contains(String keyword, Node root){
    	if(root == null)
    		return false;
    	
    	int result = keyword.compareTo(root.keyword);

    	if(result < 0){
    		return contains(keyword,root.l);
    	}
    	else if(result > 0){
    		return contains(keyword,root.r);
    	}
    	// matched
    	return true;
    }
    
    //Returns the first record for a particular keyword.
	//If the keyword is not in the bst, it should return null.
    public Record get_records(String keyword){
    	return getrecord(keyword,root);  	
    }
    
    private Record getrecord(String keyword, Node root){
    	if(!contains(keyword))
    		return null;
    	
    	int result = keyword.compareTo(root.keyword);

    	if(result < 0){
    		return getrecord(keyword,root.l);
    	}
    	else if(result > 0){
    		return getrecord(keyword,root.r);
    	}
    	// matched
    	return root.record;
    }
	//removes the Node with keyword from the binary search tree.
	//if the keyword is not in the bst, the function should do nothing.
    public void delete(String keyword){
    	if(contains(keyword)){
    		root = delete(keyword, root);
    		root.size--;
    	}
    }
    
    private Node delete(String keyword, Node root){
    	if(root == null)
    		return root;
    	
    	int result = keyword.compareTo(root.keyword);

    	if(result < 0){
    		root.l = delete(keyword,root.l);
    	}
    	else if(result > 0){
    		root.r = delete(keyword,root.r);
    	}
    	//two children
    	else if(root.l != null && root.r != null){ 
    		root.keyword = findmin(root.r).keyword;
    		root.record = findmin(root.r).record;
    		root.r = delete(root.keyword, root.r);
    	}
    	else
    		root = (root.l == null)? root.r : root.l;
    		return root;
    }
    
    //return the min in bst
    private Node findmin(Node root){
    	if(root == null)
    		return null;
    	else if(root.l == null)
    		return root;
    	return findmin(root.l);
    }
    
    public void print(){
        print(root);
    }

    private void print(Node t){
        if (t!=null){
            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;
            while(r != null){
                System.out.printf("\t%s\n",r.title);
                r = r.next;
            }
            print(t.r);
        } 
    }
}
