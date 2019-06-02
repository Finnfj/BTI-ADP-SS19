package binarySearchTree;

public class NodeArrayEmbedding implements Comparable, NodeI {
    private Integer data;
    private int key;
    private int pos;
    private int sum;
    private int bSum;
    private NodeArrayEmbedding[] nodes;

    public NodeArrayEmbedding(Integer data, NodeArrayEmbedding[] nodes) {
        this.data = data;
        key = data.hashCode();
        this.nodes = nodes;
        this.bSum = 0;
    }

    public NodeArrayEmbedding(Integer data, NodeArrayEmbedding[] nodes, int pos) {
        this.data = data;
        key = data.hashCode();
        this.nodes = nodes;
        this.pos = pos;
        this.bSum = 0;
    }

    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(obj instanceof NodeArrayEmbedding == false) return false;
        return (data.equals(((NodeArrayEmbedding) obj).getData()));
        //if(((NodeArrayEmbedding) obj).getKey() != this.key) return false;
        //return true;
    }

    public int compareTo(Object obj) {
        if(data instanceof Comparable && obj instanceof Comparable) {
            if(data.getClass() == obj.getClass()) {
                return ((Comparable) data).compareTo(obj);
            }
        }
        if(obj == null) return 0;
        if(obj instanceof NodeArrayEmbedding == false) return 0;
        return this.key - ((NodeArrayEmbedding) obj).getKey();
    }

    public int insert(NodeArrayEmbedding newNodeArrayEmbedding) {
        if(this.equals(newNodeArrayEmbedding)) { // if the node we want to add is equal to this, return this one
            return pos;
        } else { // else compare with childs
            if(compareTo(newNodeArrayEmbedding) > 0) { // -1 if newNodeNodeLinking is larger, 0 if newNodeNodeLinking is equal, 1 if newNodeNodeLinking is smaller
                if(getLeft() == null) { // if this node has no left child
                    newNodeArrayEmbedding.setPos(pos*2);
                    nodes[pos*2] = newNodeArrayEmbedding;
                    newNodeArrayEmbedding.updatebSum(newNodeArrayEmbedding.getKey());
                    return pos*2;
                } else { //if this node has a left child, give the new node to them
                    return getLeft().insert(newNodeArrayEmbedding);
                }
            } else { // if this node has no right child
                if(getRight() == null) {
                    newNodeArrayEmbedding.setPos(pos*2+1);
                    nodes[pos*2+1] = newNodeArrayEmbedding;
                    newNodeArrayEmbedding.updatebSum(newNodeArrayEmbedding.getKey());
                    return pos*2+1;
                } else { // if this node has a right child, give the new node to them
                    return getRight().insert(newNodeArrayEmbedding);
                }
            }
        }
    }

    public void printData(PrintVariant pv) {
        switch (pv) {
            case INORDER:
                if(getLeft() != null) {
                    getLeft().printData(pv);
                }
                System.out.println(data.toString() + ", " + key);
                if(getRight() != null) {
                    getRight().printData(pv);
                }
                break;
            case POSTORDER:
                if(getLeft() != null) {
                    getLeft().printData(pv);
                }
                if(getRight() != null) {
                    getRight().printData(pv);
                }
                System.out.println(data.toString() + ", " + key);
                break;
            case PREORDER:
                System.out.println(data.toString() + ", " + key);
                if(getLeft() != null) {
                    getLeft().printData(pv);
                }
                if(getRight() != null) {
                    getRight().printData(pv);
                }
                break;
        }
    }

    public Integer getData() {
        return data;
    }

    public int getKey() {
        return key;
    }

    public NodeArrayEmbedding getLeft() {
        return nodes[pos*2];
    }

    public NodeArrayEmbedding getRight() {
        return nodes[pos*2+1];
    }

    public NodeArrayEmbedding getFather() {
        return nodes[pos/2];
    }

    public void setPos(int pos) {this.pos = pos;}

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public void updateSum() {
    	NodeArrayEmbedding left = getLeft();
    	NodeArrayEmbedding right = getRight();
    	NodeArrayEmbedding father = getFather();
    	if (left != null && father != null) {
    		left.updateSum();
    		setSum(left.getbSum() + getSmallerFatherSum(getKey()) + getKey());
    	} else if (left == null && father != null) {
    		setSum(getSmallerFatherSum(getKey()) + getKey());
    	} else if (left != null && father == null) {
    		left.updateSum();
    		setSum(left.getbSum() + getKey());
    	} else {
    		setSum(getKey());
    	}
    	
    	// update right branch
    	if (right != null) {
    		getRight().updateSum();
    	}
    }
    
    public void updatebSum(int addition) {
    	NodeArrayEmbedding father = getFather();
    	
    	setbSum(getbSum() + addition);
    	if (father != null) {
    		father.updatebSum(addition);
    	}
    }
    
    public int getSmallerFatherSum(int pivot) {
    	if (getFather() != null) {
    		if (getFather().getKey() > pivot) {
    			// Bigger father, move forward
    			return getFather().getSmallerFatherSum(pivot);
    		} else {
    			// Found smaller father
    			return getFather().getSum();
    		}
    	} else {
    		return 0;
    	}
    }
    
    public NodeArrayEmbedding findClosest(Boolean highlow, int startValue) {
    	NodeArrayEmbedding result = null;
    	if (highlow) {
    		// Get higher or equal to startValue's closest node
    		if (getKey() >= startValue) {
    			// Try and find a closer value
    			if (getLeft() != null && getLeft().getKey() >= startValue) {
    				// There is a closer value, recurse
    				result = getLeft().findClosest(highlow, startValue);
    			} else {
    				// There is either no left (smaller) child or it's smaller than startValue
    				result = this;
    			}
    		} else {
    			if (getRight() != null) {
    				result = getRight().findClosest(highlow, startValue);
    			}
    		}
    	} else {
    		// Get smaller or equal to startValue's closest node
    		if (getKey() <= startValue) {
    			// Try and find a closer value
    			if (getRight() != null && getRight().getKey() <= startValue) {
    				// There is a closer value, recurse
    				result = getRight().findClosest(highlow, startValue);
    			} else {
    				// There is either no right (bigger) child or it's higher than startValue
    				result = this;
    			}
    		} else {
				if (getLeft() != null) {
					result = getLeft().findClosest(highlow, startValue);
				}
			}
    	}
		return result;
    }

	public int getbSum() {
		return bSum;
	}

	public void setbSum(int bSum) {
		this.bSum = bSum;
	}
}
