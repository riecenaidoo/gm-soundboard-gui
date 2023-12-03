package editor.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soundboard.model.catalogue.Catalogue;
import soundboard.model.catalogue.Group;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Confirm the EditableCatalogue only mutates the wrapped Catalogue.
 * when requested.
 *
 * @see EditableCatalogue
 * @see Catalogue
 */
class EditableCatalogueTest {

    Catalogue catalogue;
    EditableCatalogue editableCatalogue;
    Group group;

    @BeforeEach
    void setUp() {
        catalogue = new Catalogue();
        editableCatalogue = new EditableCatalogue(catalogue);
        group = new Group("mock");
    }

    @Test
    void addGroup() {
        editableCatalogue.addGroup(group);
        assertEquals(0, catalogue.size());

        editableCatalogue.saveChanges();
        assertEquals(1, catalogue.size());

        editableCatalogue.saveChanges();
        assertEquals(1, catalogue.size(), "Changes should only be saved once.");
    }

    @Test
    void removeGroup() {
        catalogue.add(group);

        editableCatalogue.removeGroup(group);
        assertEquals(1, catalogue.size());

        editableCatalogue.saveChanges();
        assertEquals(0, catalogue.size());

        editableCatalogue.saveChanges();
        assertEquals(0, catalogue.size(), "Changes should only be saved once.");
    }

    @Test
    void isRecentlyAdded() {
        assertFalse(editableCatalogue.isRecentlyAdded(group));
        editableCatalogue.addGroup(group);
        assertTrue(editableCatalogue.isRecentlyAdded(group));

        editableCatalogue.clearChanges();
        assertFalse(editableCatalogue.isRecentlyAdded(group));
    }

    @Test
    void isMarkedForRemoval() {
        assertFalse(editableCatalogue.isMarkedForRemoval(group));
        editableCatalogue.removeGroup(group);
        assertTrue(editableCatalogue.isMarkedForRemoval(group));

        editableCatalogue.clearChanges();
        assertFalse(editableCatalogue.isMarkedForRemoval(group));
    }
}