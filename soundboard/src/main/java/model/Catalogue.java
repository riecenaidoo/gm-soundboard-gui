package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The Catalogue represents all playlists,
 * sorted by category, that have been loaded into this application.
 */
class Catalogue {

    private final Collection<Category> categories;

    public Catalogue() {
        categories = new ArrayList<>();
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public boolean add(Category category) {
        return categories.add(category);
    }

    public boolean remove(Category category) {
        return categories.remove(category);
    }

    /**
     * @return number of Categories in this Catalogue.
     */
    public int size() {
        return categories.size();
    }

    /**
     * @return total number of Songs in this Catalogue.
     */
    public int total() {
        return categories.stream().mapToInt(Category::total).sum();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        categories.forEach(category -> {
            s.append(category.getTitle());
            s.append(", ");
        });
        s.deleteCharAt(s.lastIndexOf(", "));
        s.append("]");

        return s.toString();
    }
}

