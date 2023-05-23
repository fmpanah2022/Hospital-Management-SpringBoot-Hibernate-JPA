package org.j2os.repository.modern;

import org.j2os.domain.entity.PersonAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonAddressRepository extends JpaRepository<PersonAddress , Long> {
    PersonAddress findPersonAddressByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
    PersonAddress findPersonAddressByPerson_PersonIdAndRemoveDateTimeIsNull(Long personId);
    List<PersonAddress> findPersonAddressesByCountryAndRemoveDateTimeIsNull(String country);
    List<PersonAddress> findPersonAddressesByCityAndRemoveDateTimeIsNull(String city);
    List<PersonAddress> findPersonAddressesByStateAndRemoveDateTimeIsNull(String state);
    List<PersonAddress> findPersonAddressesByPostalCodeAndRemoveDateTimeIsNull(String postalCode);
    Optional<PersonAddress> findPersonAddressByPersonAddressIdAndRemoveDateTimeIsNull(Long personAddressId);
}
