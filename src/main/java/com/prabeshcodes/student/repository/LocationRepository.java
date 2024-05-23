//package com.prabeshcodes.student.repository;
//
//import com.prabeshcodes.student.model.Location;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface LocationRepository extends JpaRepository<Location, Long> {
//}

package com.prabeshcodes.student.repository;

import com.prabeshcodes.student.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
