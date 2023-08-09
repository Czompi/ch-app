package hu.czompi.ch_app.inventory.rules;

import hu.czompi.ch_app.inventory.InMemoryManager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Rule manager
 */
@Getter
public class RuleManager implements InMemoryManager<Rule> {
    @Setter private List<Rule> items = new ArrayList<>();

    @Override
    public Class<Rule[]> getItemClass() {
        return Rule[].class;
    }

}
