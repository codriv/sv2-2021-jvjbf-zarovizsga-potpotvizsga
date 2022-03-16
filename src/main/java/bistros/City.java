package bistros;

import java.util.*;

public class City {

    private Set<Bistro> bistros = new LinkedHashSet<>();

    public Set<Bistro> getBistros() {
        return bistros;
    }

    public void addBistro(Bistro bistro) {
        bistros.add(bistro);
    }

    public Bistro findBistroByAddress(Address address) {
        List<Bistro> found = bistros.stream().filter(bistro -> address.equals(bistro.getAddress())).toList();
        if (found.isEmpty()) {
            throw new IllegalArgumentException("Bistronot found!");
        } else {
            return found.get(0);
        }
    }

    public Bistro findLongestMenu() {
        return bistros.stream().max(Comparator.comparing(bistro -> bistro.getMenu().size())).orElseThrow(()->new IllegalStateException("Menu not found!"));
    }

    public List<Bistro> findBistroWithMenuItem(String menuItemName) {
        return bistros.stream().filter(bistro -> bistro.getMenu().stream().map(MenuItem::getName).toList().contains(menuItemName)).toList();
    }
}
