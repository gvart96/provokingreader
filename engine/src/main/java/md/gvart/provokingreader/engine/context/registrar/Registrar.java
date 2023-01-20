package md.gvart.provokingreader.engine.context.registrar;

import java.util.Set;

public interface Registrar<T> {

    void register(Set<T> items);
}
