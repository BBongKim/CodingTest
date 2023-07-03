-- 코드를 입력하세요
SELECT CRCRH.HISTORY_ID,
    CASE WHEN CRCRH.END_DATE - CRCRH.START_DATE >= 90 
         THEN ((CRCRH.END_DATE - CRCRH.START_DATE + 1) * CRCR.DAILY_FEE) - 
         (CRCRH.END_DATE - CRCRH.START_DATE + 1) * CRCR.DAILY_FEE * (SELECT TMP.DISCOUNT_RATE 
                                                                 FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN TMP 
                                                                 WHERE TMP.CAR_TYPE ='트럭' AND DURATION_TYPE = '90일 이상') * 0.01
    WHEN CRCRH.END_DATE - CRCRH.START_DATE >= 30 
        THEN ((CRCRH.END_DATE - CRCRH.START_DATE + 1) * CRCR.DAILY_FEE) - 
         (CRCRH.END_DATE - CRCRH.START_DATE + 1) * CRCR.DAILY_FEE * (SELECT TMP.DISCOUNT_RATE 
                                                                 FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN TMP 
                                                                 WHERE TMP.CAR_TYPE ='트럭' AND DURATION_TYPE = '30일 이상') * 0.01
    WHEN CRCRH.END_DATE - CRCRH.START_DATE >= 7 
        THEN ((CRCRH.END_DATE - CRCRH.START_DATE + 1) * CRCR.DAILY_FEE) - 
         (CRCRH.END_DATE - CRCRH.START_DATE + 1) * CRCR.DAILY_FEE * (SELECT TMP.DISCOUNT_RATE 
                                                                 FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN TMP 
                                                                 WHERE TMP.CAR_TYPE ='트럭' AND DURATION_TYPE = '7일 이상') * 0.01
    ELSE (CRCRH.END_DATE - CRCRH.START_DATE + 1) * CRCR.DAILY_FEE
    END AS FEE
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY CRCRH, 
CAR_RENTAL_COMPANY_CAR CRCR
WHERE CRCRH.CAR_ID = CRCR.CAR_ID AND CRCR.CAR_TYPE = '트럭'
ORDER BY FEE DESC, CRCRH.HISTORY_ID DESC;