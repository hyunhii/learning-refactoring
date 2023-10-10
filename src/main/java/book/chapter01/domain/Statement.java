package book.chapter01.domain;

import book.chapter01.dto.Invoice;
import book.chapter01.dto.Play;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

@Data
@AllArgsConstructor
public class Statement {
    private final Invoice invoice;
    private final Play[] plays;

    public String readPlainText() throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("청구 내역 (고객명: %s)\n", invoice.getCustomer());

        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en-US"));
        format.setCurrency(Currency.getInstance("USD"));
        format.setMinimumFractionDigits(2);

        for (Invoice.Performance perf : invoice.getPerformances()) {
            int thisAmount = amountFor(perf, playFor(perf));
            // 포인트 적립
            volumeCredits += Math.max(perf.getAudience() - 30, 0);

            // 희극 관객 5명마다 추가 포인트 제공
            if (playFor(perf).getType().equals("comedy")) {
                volumeCredits += Math.floor(perf.getAudience() / 5);
            }

            // 청구 내역 출력
            result +=
                    String.format(
                            "%15s:%12s%4s석\n",
                            playFor(perf).getName(), format.format(thisAmount / 100), perf.getAudience());
            totalAmount += thisAmount;
        }

        result += String.format("총액: %s\n", format.format(totalAmount / 100));
        result += String.format("적립 포인트: %s점\n", volumeCredits);
        return result;
    }

    private Play playFor(Invoice.Performance perf) {
        return Arrays.stream(plays)
                .filter(p -> p.getPlayId().equals(perf.getPlayId()))
                .findFirst()
                .get();
    }

    private int amountFor(Invoice.Performance performance, Play play) throws Exception {
        int result = 0;

        switch (play.getType()) {
            case "tragedy":
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new Exception(String.format("알 수 없는 장르: %s", play.getType()));
        }
        return result;
    }
}
