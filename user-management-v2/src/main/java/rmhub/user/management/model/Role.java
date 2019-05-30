package rmhub.user.management.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
  
  @Id
  private int id;
  
  @Enumerated(EnumType.STRING)
  private RoleName name;
  private String description;
//  @ManyToMany(fetch = FetchType.LAZY,
//      cascade = {
//          CascadeType.PERSIST,
//          CascadeType.MERGE
//      }, mappedBy = "roles")
//  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "roles")
  
//  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,  mappedBy = "roles")
////  private Set<User> users = new HashSet<User>();
//  private List<User> users = new ArrayList<User>();
}
