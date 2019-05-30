package rmhub.user.management.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "engineering_bureau")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EngineeringBureau {
  @Id
  private int id;
  private String name;
  private Date createdDate;
  private int createdBy;
  private Date lastModifiedDate;
  private int lastModifyBy;
  private String description;
//  @ManyToMany(fetch = FetchType.LAZY,
//      cascade = {
//          CascadeType.PERSIST,
//          CascadeType.MERGE
//      }, mappedBy = "engineeringBureaus")
//  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "engineeringBureaus")
//  private Set<User> users = new HashSet<User>();
//  private List<User> users = new ArrayList<User>();
}
