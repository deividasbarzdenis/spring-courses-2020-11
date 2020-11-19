package com.codeacademy.eshop;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreamsTest {

    @Test
    public void optional() {
        Optional<String> optionalOfString = Optional.ofNullable("aaaa");

        kazkoksMetodas(optionalOfString);
    }

    private void kazkoksMetodas(Optional<String> optionalOfString) {

//        optionalOfString
//                .ifPresentOrElse(daiktasViduj -> System.out.println(daiktasViduj),
//                        () -> System.out.println("Duok man ne null!"));

        String tekstas = optionalOfString.orElse("Duok man ne null!");
        System.out.println(tekstas);

//        if (optionalOfString.isPresent()) {
//            System.out.println(optionalOfString.get());
//        } else {
//            System.out.println("Duok man ne null!");
//        }

//        String aaa = Stream.of("aaa").findAny().orElse()
    }

    @Test
    public void testStreams() {
        List<MobilePhone> phones = List.of(
                new MobilePhone("Iphone 12", new BigDecimal("1000.50")),
                new MobilePhone("Samsung S10", new BigDecimal("300.99")),
                new MobilePhone("Samsung S20", new BigDecimal("999.99")));

        BigDecimal sum = BigDecimal.ZERO;

        for (MobilePhone phone : phones) {
            BigDecimal price = extractFromObjectAndLog(phone, object -> object.getPrice());

            sum = addPrice(sum, price);
        }

        System.out.println(sum);

        List<String> aaa = new ArrayList<>();
    }

    private <T> BigDecimal extractFromObjectAndLog(T objektas, Funkcija<T> funkcija) {

        BigDecimal price = funkcija.iskviesk(objektas);

        System.out.println(price);

//        dar daug logikos....
//
//
        return price;
    }

    private BigDecimal addPrice(BigDecimal sum, BigDecimal price) {
        return sum.add(price);
    }
    private static interface Funkcija<T> {

        BigDecimal iskviesk(T objektas);

    }

    static class FunkcijaSuMobilePhone implements Funkcija<MobilePhone> {

        @Override
        public BigDecimal iskviesk(MobilePhone objektas) {
            return objektas.getPrice();
        }
    }

    @Getter
    private static class MobilePhone {
        private String brand;
        private BigDecimal price;

        public MobilePhone(String brand, BigDecimal price) {
            this.brand = brand;
            this.price = price;
        }
    }
}
