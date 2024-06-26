package rs.edu.raf.exchangeservice.domain.model.myListing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "exchange_service_schema")
public class MyFuture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myFutureId;
    private String contractName;
    private Long companyId;
    private Integer amount;
    private String currencyMark;
    private Integer privateAmount;
    private Integer publicAmount;
    @Version
    private Integer version;
}
