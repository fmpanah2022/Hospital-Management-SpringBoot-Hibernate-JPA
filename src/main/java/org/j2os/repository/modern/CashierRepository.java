package org.j2os.repository.modern;

import org.j2os.domain.entity.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CashierRepository extends JpaRepository< Cashier , Long> {
    Optional<Cashier> findCashierByCashierIdAndRemoveDateTimeIsNull(Long cashierId);
    List<Cashier> findCashiersByRemoveDateTimeIsNotNull();
    Cashier findCashierByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName, String surname , int nationalCode);
    Cashier findCashierByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
    Cashier findCashierByPersonalCodeAndRemoveDateTimeIsNull(String personalCode);
}
