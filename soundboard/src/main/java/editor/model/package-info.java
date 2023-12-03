/**
 * This package contains data model classes for the CatalogueEditor.
 * <p>
 * Each model is a wrapper of an existing Soundboard data model.
 * The wrapper is needed to track changes (edits) made by the user on the CatalogueEditorView,
 * without mutating the original (wrapped) data model, or needing to clone it.
 * This allows changes made on the View to be rolled back safely.
 * </p>
 */
package editor.model;