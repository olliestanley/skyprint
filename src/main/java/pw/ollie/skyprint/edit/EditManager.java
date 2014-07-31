package pw.ollie.skyprint.edit;

import java.util.ArrayList;
import java.util.List;

public class EditManager {
	private final List<Edit> edits = new ArrayList<Edit>();

	public List<Edit> getEdits() {
		return new ArrayList<Edit>(edits);
	}

	public void add(final Edit edit) {
		edits.add(0, edit);
	}

	public void remove(final Edit edit) {
		edits.remove(edit);
	}

	public boolean contains(final Edit edit) {
		return edits.contains(edit);
	}
}
