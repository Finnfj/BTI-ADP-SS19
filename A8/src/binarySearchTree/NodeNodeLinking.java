package binarySearchTree;

public class NodeNodeLinking implements Comparable, NodeI {
    private Integer data;
    private int key;
    private int sum;
    private int bSum;
    private NodeNodeLinking left, right, father;

    public NodeNodeLinking(Integer data) {
        this.data = data;
        key = data.hashCode();
        this.father = null;
        left = null;
        right = null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(obj instanceof NodeNodeLinking == false) return false;
        return (data.equals(((NodeNodeLinking) obj).getData()));
        //if(((NodeNodeLinking) obj).getKey() != this.key) return false;
        //return true;
    }

    @Override
    public int compareTo(Object obj) {
        if(data instanceof Comparable && obj instanceof Comparable) {
            if(data.getClass() == obj.getClass()) {
                return ((Comparable) data).compareTo(obj);
            }
        }
        if(obj == null) return 0;
        if(obj instanceof NodeNodeLinking == false) return 0;
        return this.key - ((NodeNodeLinking) obj).getKey();
    }

    public boolean exist(NodeNodeLinking newNodeNodeLinking) {
        if(this.equals(newNodeNodeLinking)) {
            return true;
        } else {
            if(compareTo(newNodeNodeLinking) > 0) { // -1 if newNodeNodeLinking is smaller, 0 if newNodeNodeLinking is equal, 1 if newNodeNodeLinking is larger
                if(this.left == null) {
                    return false;
                } else {
                    return this.left.exist(newNodeNodeLinking);
                }
            } else {
                if(this.right == null) {
                    return false;
                } else {
                    return this.right.exist(newNodeNodeLinking);
                }
            }
        }
    }

    public NodeNodeLinking insert(NodeNodeLinking newNodeNodeLinking) {
        if(this.equals(newNodeNodeLinking)) { // if the node we want to add is equal to this, return this one
            return this;
        } else { // else compare with childs
            if(compareTo(newNodeNodeLinking) > 0) { // -1 if newNodeNodeLinking is larger, 0 if newNodeNodeLinking is equal, 1 if newNodeNodeLinking is smaller
                if(left == null) { // if this node has no left child
                    left = newNodeNodeLinking;
                    left.setFather(this);
                    newNodeNodeLinking.updatebSum();
                    return left;
                } else { //if this node has a left child, give the new node to them
                    return left.insert(newNodeNodeLinking);
                }
            } else { // if this node has no right child
                if(right == null) {
                    right = newNodeNodeLinking;
                    right.setFather(this);
                    newNodeNodeLinking.updatebSum();
                    return right;
                } else { // if this node has a right child, give the new node to them
                    return right.insert(newNodeNodeLinking);
                }
            }
        }
    }

    public void printData(PrintVariant pv) {
        switch (pv) {
            case INORDER:
                if(left != null) {
                    left.printData(pv);
                }
                System.out.println(data.toString() + ", " + key);
                if(right != null) {
                    right.printData(pv);
                }
                break;
            case POSTORDER:
                if(left != null) {
                    left.printData(pv);
                }
                if(right != null) {
                    right.printData(pv);
                }
                System.out.println(data.toString() + ", " + key);
                break;
            case PREORDER:
                System.out.println(data.toString() + ", " + key);
                if(left != null) {
                    left.printData(pv);
                }
                if(right != null) {
                    right.printData(pv);
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

    public NodeNodeLinking getLeft() {
        return left;
    }

    public void setLeft(NodeNodeLinking left) {
        this.left = left;
    }

    public NodeNodeLinking getRight() {
        return right;
    }

    public void setRight(NodeNodeLinking right) {
        this.right = right;
    }

    public NodeNodeLinking getFather() {
        return father;
    }

    public void setFather(NodeNodeLinking father) {
        this.father = father;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    public void updateSum() {
    	NodeNodeLinking left = getLeft();
    	NodeNodeLinking right = getRight();
    	NodeNodeLinking father = getFather();
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
    
    public void updatebSum() {
    	int thisval = getKey();
    	NodeNodeLinking father = getFather();
    	
    	setbSum(getbSum() + getKey());
    	
    	while(father != null) {
    		father.setbSum(father.getbSum() + thisval);
    		father = father.getFather();
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
    
    public NodeNodeLinking findClosest(Boolean highlow, int startValue) {
    	NodeNodeLinking result = null;
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
