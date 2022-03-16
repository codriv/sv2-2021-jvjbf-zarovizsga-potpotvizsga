package settlers;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SettlerRepository {

    private JdbcTemplate jdbcTemplate;

    public SettlerRepository(MariaDbDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long saveNewSettler(Settler settler) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("insert into settlers (name_of_settler, amount_of_tobacco, expected_income) values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, settler.getNameOfSettler());
                ps.setInt(2, settler.getAmountOfTobacco());
                ps.setInt(3, settler.getExpectedIncome());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public Settler findSettlerById(long id) {
        List<Settler> settlersFound = jdbcTemplate.query("select * from settlers where id = ?", (rs, rowNum) -> {
            String nameOfSettler = rs.getString("name_of_settler");
            int amountOfTobacco = rs.getInt("amount_of_tobacco");
            return new Settler(nameOfSettler, amountOfTobacco);
        }, id);
        if (settlersFound.isEmpty()) {
            throw new IllegalArgumentException("Settler not found!");
        } else {
            return settlersFound.get(0);
        }
    }

    public void updateGrowthAndIncome(long id, int amount) {
        jdbcTemplate.update("update settlers set amount_of_tobacco = amount_of_tobacco - ? where id = ?", amount, id);
        Settler settler = findSettlerById(id);
        jdbcTemplate.update("update settlers set expected_income = ? where id = ?", settler.getExpectedIncome(), id);
    }
}
