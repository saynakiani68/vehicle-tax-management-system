
package org.example.vehicletax;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnnualTaxListing {

    private final LocalDate generatedOn = LocalDate.now();

    private final List<OwnerTaxEntry> ownerEntries =
            new ArrayList<>();

    public LocalDate getGeneratedOn() {
        return generatedOn;
    }

    public OwnerTaxEntry addOwnerEntry(Person owner) {
        OwnerTaxEntry entry = new OwnerTaxEntry(owner);
        ownerEntries.add(entry);
        return entry;
    }

    public List<OwnerTaxEntry> getOwnerEntries() {
        return List.copyOf(ownerEntries);
    }
}
