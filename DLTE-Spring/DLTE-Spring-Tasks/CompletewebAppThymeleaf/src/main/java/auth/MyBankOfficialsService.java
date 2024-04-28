package auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankOfficialsService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MyBankOfficials signingUp(MyBankOfficials myBankOfficials){
        int ack = jdbcTemplate.update("insert into my_bank_officials values(?,?,?,?,?,?)",new Object[]{
                myBankOfficials.getUsername(),
                myBankOfficials.getPassword(),
                myBankOfficials.getRole(),
                myBankOfficials.getAddress(),
                myBankOfficials.getContact(),
                myBankOfficials.getEmail()
        });
        return myBankOfficials;
    }
    public MyBankOfficials findByUsername(String username){
        MyBankOfficials myBankOfficials = jdbcTemplate.queryForObject("select * from my_bank_officials where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankOfficials.class));
        return myBankOfficials;
    }
    public void updateAttempts(MyBankOfficials myBankOfficials){
        jdbcTemplate.update("update mybank_officials set attempts=? where username=?",
                new Object[]{myBankOfficials.getAttempts(),myBankOfficials.getUsername()});

    }

    public void updateStatus(MyBankOfficials myBankOfficials){
        jdbcTemplate.update("update mybank_officials set status=0 where username=?",
                new Object[]{myBankOfficials.getUsername()});

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankOfficials officials = findByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
}