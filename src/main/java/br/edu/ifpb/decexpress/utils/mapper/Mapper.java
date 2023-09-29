package br.edu.ifpb.decexpress.utils.mapper;

import java.util.Collection;
import java.util.List;

public interface Mapper <T,U>{
    U map (T object);

    List<U> mapCollection(Collection<T> objects);
}
