package pw.ollie.skyprint.edit;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all SkyPrint Edits and provides some functionality for working with
 * them
 */
public class EditManager {
	/**
	 * An {@link ArrayList} of SkyPrint {@link Edit} objects in this session
	 */
	private final List<Edit> edits = new ArrayList<Edit>();

	/**
	 * Gets a copy of the {@link ArrayList} of all SkyPrint Edits in this
	 * session
	 * 
	 * @return A copy of the {@link ArrayList} of all SkyPrint Edits in this
	 *         session
	 */
	public List<Edit> getEdits() {
		return new ArrayList<Edit>(edits);
	}

	/**
	 * Adds the given {@link Edit} to the Edits {@link ArrayList}
	 * 
	 * @param edit
	 *            The {@link Edit} to add to the List
	 */
	public void add(final Edit edit) {
		edits.add(0, edit);
	}

	/**
	 * Removes the given {@link Edit} from the Edits {@link ArrayList}
	 * 
	 * @param edit
	 *            The {@link Edit} to remove from the List
	 */
	public void remove(final Edit edit) {
		edits.remove(edit);
	}

	/**
	 * Checks whether the {@link ArrayList} of SkyPrint {@link Edit} objects
	 * contains the given {@link Edit}
	 * 
	 * @param edit
	 *            The {@link Edit} to check for
	 * @return True if the Edits {@link ArrayList} contains the given
	 *         {@link Edit}, otherwise false
	 */
	public boolean contains(final Edit edit) {
		return edits.contains(edit);
	}
}
