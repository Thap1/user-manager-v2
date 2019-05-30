package rmhub.user.management.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
  @Id
  private int id;
  private String loginName;
  private String password;
  private String email;
  private String phone;
  private Date createDate;
  private int createBy;
  private Date lastModifiedDate;
  private int lastModifiedBy;
  private int isActive;
//  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//  @ManyToMany(fetch = FetchType.EAGER,
//      cascade = {
//          CascadeType.PERSIST,
//          CascadeType.MERGE
//      })
//  @JoinTable(name = "users_roles", joinColumns = {
//      @JoinColumn(name = "user_id")
//  }, inverseJoinColumns = {@JoinColumn(name = "roles_id")})
  @ManyToMany(fetch = FetchType.EAGER)
//  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
//  inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<Role>();
//  private List<Role> roles = new ArrayList<Role>();
  
//  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//  @ManyToMany(fetch = FetchType.EAGER,
//      cascade = {
//          CascadeType.PERSIST,
//          CascadeType.MERGE
//      })
//  @JoinTable(name = "users_engineering_bureaus", joinColumns = {
//      @JoinColumn(name = "user_id")
//  }, inverseJoinColumns = {@JoinColumn(name = "engineering_bureaus_id")})
  @ManyToMany(fetch = FetchType.EAGER)
//  @JoinTable(name = "users_engineering_bureaus", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
//  inverseJoinColumns = @JoinColumn(name = "engineering_bureaus_id", referencedColumnName = "id"))
  private Set<EngineeringBureau> engineeringBureaus = new HashSet<EngineeringBureau>();
//  private List<EngineeringBureau> engineeringBureaus = new ArrayList<EngineeringBureau>();
}
