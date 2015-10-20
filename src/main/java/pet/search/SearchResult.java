
package pet.search;

import java.util.ArrayList;

public class SearchResult {
    
    private ArrayList<Integer> indexes;

    public SearchResult() {
        this.indexes = new ArrayList<Integer>();
    }
    
    public SearchResult(ArrayList<Integer> indexes) {
        this.indexes = indexes;
    }

    public boolean addIndex(int index){
        return this.indexes.add(index);
    }

    public ArrayList<Integer> getIndexes(){
        return indexes;
    }
}
