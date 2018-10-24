package homework.repository;

import homework.entity.Scheme;
import org.springframework.data.repository.CrudRepository;

public interface SchemeRepository extends CrudRepository<Scheme,Integer> {
}
