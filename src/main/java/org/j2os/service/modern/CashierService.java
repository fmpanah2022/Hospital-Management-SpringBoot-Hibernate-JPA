package org.j2os.service.modern;

import org.j2os.domain.entity.Cashier;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CashierService {
    Map<String, Object> convertCashierToMap(Cashier cashier);
    List<Map<String, Object>> convertCashierListToMapList(List<Cashier> cashiers);
    Cashier findCashierByPersonalCodeAndRemoveDateTimeIsNull(String personalCode);
    Optional<Cashier> findCashierByCashierIdAndRemoveDateTimeIsNull(Long cashierId);
    List<Cashier> findCashiersByRemoveDateTimeIsNotNull();
    Cashier findCashierByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName, String surname , int nationalCode);
    Cashier findCashierByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
    Cashier save (Cashier cashier);
    Cashier update (Cashier cashier);
    void removeLogical(Cashier cashier);
    boolean existsCashier ( Cashier cashier);
    boolean conflictCashier ( Cashier cashier);
    boolean canRemove( Cashier cashier);
}
