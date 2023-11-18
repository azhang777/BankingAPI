package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Bill;
import com.bankapi.bankofmikaila.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillService {

    private static final Map<Long, Bill> billRepository = new HashMap<>();
    private static long nextBillId;

    public List<Bill> getAllBills() {
        return new ArrayList<>(billRepository.values());
    }
    public Bill getBillById(Long id) {
        return billRepository.get(id);
    }

    public Bill createBill(Bill bill) {
        long newBillId = nextBillId++;
        bill.setId(newBillId);
        billRepository.put(newBillId, bill);
        return bill;
    }

    public Bill updateBill(Long id, Bill updatedBill) {
        if (billRepository.containsKey(id)) {
            updatedBill.setId(id);
            billRepository.put(id, updatedBill);
            return updatedBill;
        } else {
            return null;
        }
    }

    public boolean deleteBill(Long id) {
        if (billRepository.containsKey(id)) {
            billRepository.remove(id);
            return true;
        } else {
            return false;
        }
    }
}

