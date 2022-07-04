package com.example.kubeServicesNew;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KubeServicesJPAData extends JpaRepository<KubeServices, Long>{
	List<KubeServices> findByCoursename(String coursename);
}