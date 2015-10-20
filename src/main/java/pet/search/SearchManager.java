package pet.search;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import pet.frontend.WordSearchPage;
import pet.frontend.components.AbstractUnitGUI;
import pet.usr.adapter.EditableUnit;

public class SearchManager {

    private WordSearchPage page;
    private String currentSearch;
    private HashMap<Integer, SearchResult> searchMap;
    private int first;
    private int last;

    public SearchManager(WordSearchPage wsp) {
        this.page = wsp;
        this.currentSearch = "";
        this.searchMap = new HashMap<Integer, SearchResult>();
        this.first = -1;
        this.last = -1;
    }

    public void getNext(String search) {
        //Update search if necessary:
        if (!currentSearch.equals(search)) {
            this.performSearch(search);
        }

        //Get current task in editing:
        Integer currentTask = this.page.getAnnotationPage().getPool().getPointer();

        //Check to see if there are further tasks:
        int lastTask = this.page.getAnnotationPage().getTasks().size() - 1;
        if (currentTask <= lastTask) {
            //Walk until fall off borders or find search entry:
            int i = currentTask + 1;
            SearchResult result = this.searchMap.get(i);
            while (i <= lastTask && result == null) {
                i++;
                result = this.searchMap.get(i);
            }

            //If none were found, move back to first if available:
            if (result == null && this.searchMap.keySet().size() > 0) {
                int gap = -1 - currentTask;
                this.moveAnnotationPage(gap);
                currentTask = this.page.getAnnotationPage().getPool().getPointer();
                i = this.first;
                result = this.searchMap.get(i);
            }

            //If there are further successful searches, move panel in the annotation page:
            if (result != null) {
                this.page.updateResultsLabel("Found " + result.getIndexes().size() + " occurrences in task " + i + ".");
                int gap = i - currentTask;
                this.moveAnnotationPage(gap);
            } else {
                this.page.updateResultsLabel("No further tasks with occurrences.");
            }
        }
    }

    public void getPrevious(String search) {
        //Update search if necessary:
        if (!currentSearch.equals(search)) {
            this.performSearch(search);
        }

        //Get current task in editing:
        Integer currentTask = this.page.getAnnotationPage().getPool().getPointer();

        //Check to see if there are further tasks:
        if (currentTask > -1) {
            //Walk until fall off borders or find search entry:
            int i = currentTask - 1;
            SearchResult result = this.searchMap.get(i);
            while (i >= 0 && result == null) {
                i--;
                result = this.searchMap.get(i);
            }

            //If none were found, move back to first if available:
            if (result == null && this.searchMap.keySet().size() > 0) {
                int gap = this.page.getAnnotationPage().getTasks().size() - currentTask;
                this.moveAnnotationPage(gap);
                currentTask = this.page.getAnnotationPage().getPool().getPointer();
                i = this.last;
                result = this.searchMap.get(i);
            }

            //If there are further successful searches, move panel in the annotation page:
            if (result != null) {
                this.page.updateResultsLabel("Found " + result.getIndexes().size() + " occurrences in task " + i + ".");
                int gap = i - currentTask;
                this.moveAnnotationPage(gap);
            } else {
                this.page.updateResultsLabel("No further tasks with occurrences.");
            }
        }
    }

    public void getFirst(String search) {
        //Update search if necessary:
        if (!currentSearch.equals(search)) {
            this.performSearch(search);
        }

        //Get current task in editing:
        Integer currentTask = this.page.getAnnotationPage().getPool().getPointer();

        //If there is at least one search result:
        if (this.first > -1) {
            //Get search result:
            SearchResult result = this.searchMap.get(this.first);
            this.page.updateResultsLabel("Found " + result.getIndexes().size() + " occurrences in task " + this.first + ".");
            int gap = this.first - currentTask;
            this.moveAnnotationPage(gap);
        } else {
            this.page.updateResultsLabel("No occurrences were found.");
        }
    }

    public void getLast(String search) {
        //Update search if necessary:
        if (!currentSearch.equals(search)) {
            this.performSearch(search);
        }

        //Get current task in editing:
        Integer currentTask = this.page.getAnnotationPage().getPool().getPointer();

        //If there is at least one search result:
        if (this.last > -1) {
            //Get search result:
            SearchResult result = this.searchMap.get(this.last);
            this.page.updateResultsLabel("Found " + result.getIndexes().size() + " occurrences in task " + this.last + ".");
            int gap = this.last - currentTask;
            this.moveAnnotationPage(gap);
        } else {
            this.page.updateResultsLabel("No occurrences were found.");
        }
    }

    private void performSearch(String search) {
        //Reset map:
        this.searchMap = new HashMap<Integer, SearchResult>();

        //Update current search:
        this.currentSearch = search;

        //Get tasks to be searched over:
        List<EditableUnit> tasks = this.page.getAnnotationPage().getTasks();

        //Perform searches:
        for (int i = 0; i < tasks.size(); i++) {
            //Get translations in their current state:
            String translation = tasks.get(i).getEditing().toString();

            //Create array list of found indexes:
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            int index = translation.indexOf(search);
            while (index > -1) {
                indexes.add(index);
                index = translation.indexOf(search, index + 1);
            }

            //Add results to search map:
            if (indexes.size() > 0) {
                this.searchMap.put(i, new SearchResult(indexes));
            }
        }

        //Get first and last instances found:
        int min = 999999;
        int max = -999999;
        for (Integer i : this.searchMap.keySet()) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        if (min != 999999) {
            this.first = min;
        } else {
            this.first = -1;
        }
        if (max != -999999) {
            this.last = max;
        } else {
            this.last = -1;
        }
    }

    private void moveAnnotationPage(int gap) {
        //If gap is positive, move annotation interface further:
        if (gap > 0) {
            for (int i = 0; i < gap; i++) {
                this.page.getAnnotationPage().move(true, true);
            }
            //If gap is negative, move annotation interface backwards:
        } else if (gap < 0) {
            for (int i = 0; i < -1 * gap; i++) {
                this.page.getAnnotationPage().move(false, true);
            }
        }
    }

    private void highlightFoundItems(SearchResult result, String search) {
        //Get editable panel:
        int editablePosition = this.page.getAnnotationPage().getEditablePosition();
        AbstractUnitGUI editable = (AbstractUnitGUI) this.page.getAnnotationPage().getAlignments().get(editablePosition).getKey(2);

        //Get highlighter:
        Highlighter highlighter = editable.getHighlighter();
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);

        //Get occurrence indexes:
        ArrayList<Integer> indexes = result.getIndexes();

        //Higlight each occurrence:
        for (Integer i : indexes) {
            try {
                highlighter.addHighlight(i, i + search.length(), painter);
            } catch (BadLocationException ex) {
                Logger.getLogger(SearchManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateSearch() {
        //Get editable panel:
        int editablePosition = this.page.getAnnotationPage().getEditablePosition();

        //Get current task:
        int currentTask = this.page.getAnnotationPage().getPool().getPointer();

        //Get current task:
        EditableUnit task = this.page.getAnnotationPage().getTasks().get(currentTask);

        //Check to see if there are matches in search map:
        if (this.searchMap.get(currentTask) != null) {
            //Get translations in their current state:
            String translation = task.getEditing().toString();

            //Create array list of found indexes:
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            int index = translation.indexOf(getCurrentSearch());
            while (index > -1) {
                indexes.add(index);
                index = translation.indexOf(getCurrentSearch(), index + 1);
            }

            //Update map entry:
            if (indexes.size() > 0) {
                this.searchMap.put(currentTask, new SearchResult(indexes));
            } else {
                this.searchMap.remove(currentTask);
            }

            //Get first and last instances found:
            int min = 999999;
            int max = -999999;
            for (Integer i : this.searchMap.keySet()) {
                if (i < min) {
                    min = i;
                }
                if (i > max) {
                    max = i;
                }
            }
            if (min != 999999) {
                this.first = min;
            } else {
                this.first = -1;
            }
            if (max != -999999) {
                this.last = max;
            } else {
                this.last = -1;
            }
        }
    }

    public SearchResult getSearchResult(int i) {
        return this.searchMap.get(i);
    }

    /**
     * @return the currentSearch
     */
    public String getCurrentSearch() {
        return currentSearch;
    }
}
