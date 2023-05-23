package org.j2os.service.modern;

import org.j2os.domain.entity.PersonAddress;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PersonAddressService {
    Map<String, Object> convertPersonAddressToMap(PersonAddress address);
    List<Map<String, Object>> convertPersonAddressListToMapList(List<PersonAddress> addresses);
    PersonAddress findPersonAddressByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
    PersonAddress findPersonAddressByPerson_PersonIdAndRemoveDateTimeIsNull(Long personId);
    List<PersonAddress> findPersonAddressesByCountryAndRemoveDateTimeIsNull(String country);
    List<PersonAddress> findPersonAddressesByCityAndRemoveDateTimeIsNull(String city);
    List<PersonAddress> findPersonAddressesByStateAndRemoveDateTimeIsNull(String state);
    List<PersonAddress> findPersonAddressesByPostalCodeAndRemoveDateTimeIsNull(String postalCode);
    Optional<PersonAddress> findPersonAddressByPersonAddressIdAndRemoveDateTimeIsNull(Long personAddressId);
    PersonAddress save (PersonAddress personAddress);
    PersonAddress update (PersonAddress personAddress) ;
    void removeLogical (PersonAddress personAddress) ;
}
