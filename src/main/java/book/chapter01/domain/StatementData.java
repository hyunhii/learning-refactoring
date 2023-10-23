package book.chapter01.domain;

import book.chapter01.dto.Invoice;
import book.chapter01.dto.Performance;
import book.chapter01.dto.Play;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public class StatementData {

    private final Invoice invoice;
    private final Play[] plays;

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public Performance[] getPerformances() {
        return invoice.getPerformances();
    }

    public int totalAmount() throws Exception {
        int totalAmount = 0;
        for (Performance perf : invoice.getPerformances()) {
            totalAmount += amountFor(perf);
        }
        return totalAmount;
    }

    public int totalVolumeCredits() {
        int volumeCredits = 0;
        for (Performance perf : invoice.getPerformances()) {
            volumeCredits += volumeCreditsFor(perf);
        }
        return volumeCredits;
    }

    private int volumeCreditsFor(Performance performance) {
        // 포인트 적립
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);

        // 희극 관객 5명마다 추가 포인트 제공
        if (playFor(performance).getType().equals("comedy")) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }

    public Play playFor(Performance perf) {
        return Arrays.stream(plays)
                .filter(p -> p.getPlayId().equals(perf.getPlayId()))
                .findFirst()
                .get();
    }

    public int amountFor(Performance performance) throws Exception {
        return new PerformanceCalculator(performance, playFor(performance)).getAmount();
    }
}
