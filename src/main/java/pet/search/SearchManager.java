package pet.search;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private HashMap<Integer, SearchResult> srcSearchMap;
    private HashMap<Integer, SearchResult> tgtSearchMap;
    private int first;
    private int last;

    public SearchManager(WordSearchPage wsp) {
        this.page = wsp;
        this.currentSearch = "";
        this.srcSearchMap = new HashMap<Integer, SearchResult>();
        this.tgtSearchMap = new HashMap<Integer, SearchResult>();
        this.first = -1;
        this.last = -1;
    }

    public void getNext(String search) {
        //Update search if necessary:
        if (!currentSearch.equals(search) || this.page.getSettingsChanged()) {
            this.performSearch(search);
            this.page.setSettingsChanged(false);
        }

        //Get current task in editing:
        Integer currentTask = this.page.getAnnotationPage().getPool().getPointer();

        //Check to see if there are further tasks:
        int lastTask = this.page.getAnnotationPage().getTasks().size() - 1;
        if (currentTask <= lastTask) {
            //Walk until fall off borders or find search entry:
            int i = currentTask + 1;
            SearchResult srcResult = this.srcSearchMap.get(i);
            SearchResult tgtResult = this.srcSearchMap.get(i);
            while (i <= lastTask && srcResult == null && tgtResult == null) {
                i++;
                srcResult = this.srcSearchMap.get(i);
                tgtResult = this.tgtSearchMap.get(i);
            }

            //If none were found, move back to first if available:
            if (srcResult == null && tgtResult == null && (this.srcSearchMap.keySet().size() > 0 || this.tgtSearchMap.keySet().size() > 0)) {
                int gap = -1 - currentTask;
                this.moveAnnotationPage(gap);
                currentTask = this.page.getAnnotationPage().getPool().getPointer();
                i = this.first;
                srcResult = this.srcSearchMap.get(i);
                tgtResult = this.tgtSearchMap.get(i);
            }

            //If there are further successful searches, move panel in the annotation page:
            if (srcResult != null || tgtResult != null) {
                int size = 0;
                if (srcResult != null) {
                    size += srcResult.getIndexes().size();
                }
                if (tgtResult != null) {
                    size += tgtResult.getIndexes().size();
                }
                this.page.updateResultsLabel("Found " + size + " occurrences in task " + i + ".");
                int gap = i - currentTask;
                this.moveAnnotationPage(gap);
            } else {
                this.page.updateResultsLabel("No further tasks with occurrences.");
            }
        }
    }

    public void getPrevious(String search) {
        //Update search if necessary:
        if (!currentSearch.equals(search) || this.page.getSettingsChanged()) {
            this.performSearch(search);
            this.page.setSettingsChanged(false);
        }

        //Get current task in editing:
        Integer currentTask = this.page.getAnnotationPage().getPool().getPointer();

        //Check to see if there are further tasks:
        if (currentTask > -1) {
            //Walk until fall off borders or find search entry:
            int i = currentTask - 1;
            SearchResult srcResult = this.srcSearchMap.get(i);
            SearchResult tgtResult = this.srcSearchMap.get(i);
            while (i >= 0 && srcResult == null && tgtResult == null) {
                i--;
                srcResult = this.srcSearchMap.get(i);
                tgtResult = this.tgtSearchMap.get(i);
            }

            //If none were found, move back to first if available:
            if (srcResult == null && tgtResult == null && (this.srcSearchMap.keySet().size() > 0 || this.tgtSearchMap.keySet().size() > 0)) {
                int gap = this.page.getAnnotationPage().getTasks().size() - currentTask;
                this.moveAnnotationPage(gap);
                currentTask = this.page.getAnnotationPage().getPool().getPointer();
                i = this.last;
                srcResult = this.srcSearchMap.get(i);
                tgtResult = this.tgtSearchMap.get(i);
            }

            //If there are further successful searches, move panel in the annotation page:
            if (srcResult != null || tgtResult != null) {
                int size = 0;
                if (srcResult != null) {
                    size += srcResult.getIndexes().size();
                }
                if (tgtResult != null) {
                    size += tgtResult.getIndexes().size();
                }
                this.page.updateResultsLabel("Found " + size + " occurrences in task " + i + ".");
                int gap = i - currentTask;
                this.moveAnnotationPage(gap);
            } else {
                this.page.updateResultsLabel("No further tasks with occurrences.");
            }
        }
    }

    public void getFirst(String search) {
        //Update search if necessary:
        if (!currentSearch.equals(search) || this.page.getSettingsChanged()) {
            this.performSearch(search);
            this.page.setSettingsChanged(false);
        }

        //Get current task in editing:
        Integer currentTask = this.page.getAnnotationPage().getPool().getPointer();

        //If there is at least one search result:
        if (this.first > -1) {
            //Get search result:
            SearchResult srcResult = this.srcSearchMap.get(this.first);
            SearchResult tgtResult = this.tgtSearchMap.get(this.first);
            int size = 0;
            if (srcResult != null) {
                size += srcResult.getIndexes().size();
            }
            if (tgtResult != null) {
                size += tgtResult.getIndexes().size();
            }
            this.page.updateResultsLabel("Found " + size + " occurrences in task " + this.first + ".");
            int gap = this.first - currentTask;
            this.moveAnnotationPage(gap);
        } else {
            this.page.updateResultsLabel("No occurrences were found.");
        }
    }

    public void getLast(String search) {
        //Update search if necessary:
        if (!currentSearch.equals(search) || this.page.getSettingsChanged()) {
            this.performSearch(search);
            this.page.setSettingsChanged(false);
        }

        //Get current task in editing:
        Integer currentTask = this.page.getAnnotationPage().getPool().getPointer();

        //If there is at least one search result:
        if (this.last > -1) {
            //Get search result:
            SearchResult srcResult = this.srcSearchMap.get(this.first);
            SearchResult tgtResult = this.tgtSearchMap.get(this.first);
            int size = 0;
            if (srcResult != null) {
                size += srcResult.getIndexes().size();
            }
            if (tgtResult != null) {
                size += tgtResult.getIndexes().size();
            }
            this.page.updateResultsLabel("Found " + size + " occurrences in task " + this.last + ".");
            int gap = this.last - currentTask;
            this.moveAnnotationPage(gap);
        } else {
            this.page.updateResultsLabel("No occurrences were found.");
        }
    }

    private void performSearch(String search) {
        //Reset maps:
        this.srcSearchMap = new HashMap<Integer, SearchResult>();
        this.tgtSearchMap = new HashMap<Integer, SearchResult>();

        //Update current search:
        this.currentSearch = search;

        //Get search settings:
        boolean searchSource = this.page.searchSource();
        boolean searchTarget = this.page.searchTarget();

        //Get tasks to be searched over:
        List<EditableUnit> tasks = this.page.getAnnotationPage().getTasks();

        //Perform searches:
        for (int i = 0; i < tasks.size(); i++) {
            //Get translations in their current state:
            String source = tasks.get(i).getSource().toString();
            String translation = tasks.get(i).getEditing().toString();

            //If lowercase required, format sentences:
            if (this.page.getCaseInsensitive()) {
                source = source.toLowerCase();
                translation = translation.toLowerCase();
            }

            //Create array list of found indexes:
            ArrayList<Integer> srcIndexes = new ArrayList<Integer>();
            if (searchSource) {
                if (!this.page.getFullMatch()) {
                    int index = source.indexOf(search);
                    while (index > -1) {
                        srcIndexes.add(index);
                        index = source.indexOf(search, index + 1);
                    }
                } else {
                    String[] tokens = source.split(" ");
                    int cindex = 0;
                    for(String token: tokens){
                        if(token.equals(search)){
                            srcIndexes.add(cindex);
                        }
                        cindex += token.length() + 1;
                    }
                }
            }

            //Create array list of found indexes:
            ArrayList<Integer> tgtIndexes = new ArrayList<Integer>();
            if (searchTarget) {
                if (!this.page.getFullMatch()) {
                    int index = translation.indexOf(search);
                    while (index > -1) {
                        tgtIndexes.add(index);
                        index = translation.indexOf(search, index + 1);
                    }
                } else {
                    String[] tokens = translation.split(" ");
                    int cindex = 0;
                    for(String token: tokens){
                        if(token.equals(search)){
                            tgtIndexes.add(cindex);
                        }
                        cindex += token.length() + 1;
                    }
                }
            }

            //Add results to search map:
            if (srcIndexes.size() > 0) {
                this.srcSearchMap.put(i, new SearchResult(srcIndexes));
            }
            if (tgtIndexes.size() > 0) {
                this.tgtSearchMap.put(i, new SearchResult(tgtIndexes));
            }
        }

        //Get first and last instances found:
        int min = 999999;
        int max = -999999;
        for (Integer i : this.srcSearchMap.keySet()) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        for (Integer i : this.tgtSearchMap.keySet()) {
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
        if (this.tgtSearchMap.get(currentTask) != null) {
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
                this.tgtSearchMap.put(currentTask, new SearchResult(indexes));
            } else {
                this.tgtSearchMap.remove(currentTask);
            }

            //Get first and last instances found:
            int min = 999999;
            int max = -999999;
            for (Integer i : this.srcSearchMap.keySet()) {
                if (i < min) {
                    min = i;
                }
                if (i > max) {
                    max = i;
                }
            }
            for (Integer i : this.tgtSearchMap.keySet()) {
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

    public SearchResult getSourceSearchResult(int i) {
        return this.srcSearchMap.get(i);
    }

    public SearchResult getTargetSearchResult(int i) {
        return this.tgtSearchMap.get(i);
    }

    /**
     * @return the currentSearch
     */
    public String getCurrentSearch() {
        return currentSearch;
    }
}
