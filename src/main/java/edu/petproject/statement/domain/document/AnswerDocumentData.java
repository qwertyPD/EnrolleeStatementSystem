package edu.petproject.statement.domain.document;

import java.util.ArrayList;
import java.util.List;

public class AnswerDocumentData {
    private List<AnswerDocumentDataItem> items;

    public List<AnswerDocumentDataItem> getItems() {
        return items;
    }

    public void addItems(AnswerDocumentDataItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>(10);
        } else {
            this.items.add(item);
        }
    }
}
