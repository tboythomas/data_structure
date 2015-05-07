//wenlu cheng, ID:1336340
//HW #3
//This file is a basic data structure of a binary search tree that is consisted of nodes
//contain a keyword and record

class bst{
    
    Node root;

    private class Node{
    	
    	// These attributes of the Node class will not be sufficient for those attempting the AVL extra credit.
    	// You are free to add extra attributes as you see fit, but do not remove attributes given as it will mess with help code.
        String keyword;
        Record record;
        int size;
        Node l;
        Node r;

        private Node(String k){
        	// TODO Instantialize a new Node with keyword k.
        	keyword = k;
            size = 0;
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
            size++;
        }     
    }

    public bst(){
        this.root = null;
    }
    
    // updates the record if exists, add a new node otherwise  
    public void insert(String keyword, FileData fd){
        Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);
        //TODO Write a recursive insertion that adds recordToAdd to the list of records for the node associated
        //with keyword. If there is no node, this code should add the node.
        root = insert(keyword, root, recordToAdd);
    }
    
    private Node insert(String keyword,Node root, Record recordtoadd){
    	if(root == null){
    		Node temp = new Node(keyword);
    		temp.update(recordtoadd);
    		return temp;
    	}
    	
    	int result = keyword.compareToIgnoreCase(root.keyword);
    	
    	if(result < 0 ){
    		root.l = insert(keyword,root.l,recordtoadd);
    	}
    	else if (result > 0){
    		root.r = insert(keyword,root.r,recordtoadd);
    	}
    	else // find the node and add the record
    		root.update(recordtoadd);
    		return root;
    }
    
    //return true if contains node has keyword, fasle if not
    public boolean contains(String keyword){
    	//TODO Write a recursive function which returns true if a particular keyword exists in the bst
    	return contains(keyword,root);
    }
    
    private boolean contains(String keyword, Node root){
    	if(root == null)
    		return false;
    	
    	int result = keyword.compareToIgnoreCase(root.keyword);

    	if(result < 0){
    		return contains(keyword,root.l);
    	}
    	else if(result > 0){
    		return contains(keyword,root.r);
    	}
    	// contains return true
    	return true;
    }
    
    //return the record of the node has the keyword
    public Record get_records(String keyword){
        //TODO Returns the first record for a particular keyword. This record will link to other records
    	//If the keyword is not in the bst, it should return null.
    	return getrecord(keyword,root);  	
    }
    
    private Record getrecord(String keyword, Node root){
    	if(!contains(keyword))
    		return null;
    	
    	int result = keyword.compareToIgnoreCase(root.keyword);

    	if(result < 0){
    		return getrecord(keyword,root.l);
    	}
    	else if(result > 0){
    		return getrecord(keyword,root.r);
    	}
    	// matched
    	return root.record;
    }
    
    //delete the node has the keyword, if doesnt contains, do nothing
    public void delete(String keyword){
    	//TODO Write a recursive function which removes the Node with keyword from the binary search tree.
    	//You may not use lazy deletion and if the keyword is not in the bst, the function should do nothing.
    	if(contains(keyword)){
    		root = delete(keyword, root);
    	}
    }
    
    private Node delete(String keyword, Node root){
    	if(root == null)
    		return root;
    	
    	int result = keyword.compareToIgnoreCase(root.keyword);

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
    
    private Node findmin(Node root){
    	if(root == null)
    		return null;
    	else if(root.l == null)
    		return root;
    	return findmin(root.l);
    }
    
    //print the whole tree in order
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
