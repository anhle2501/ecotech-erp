package vn.com.ecotechgroup.erp.controller;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.annotation.SecurityTestExecutionListeners;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test accessing the /admin/order/{id}/confirm with correct authority (order:confirm).
     */
    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN", "order:confirm"})
    public void testOrderConfirmAccessWithAuthority() throws Exception {
        mockMvc.perform(get("/admin/order/1/confirm"))
                .andExpect(status().isFound()); // Assuming access is granted and a 200 OK response
    }

    /**
     * Test accessing the /admin/order/{id}/confirm without correct authority (order:confirm).
     */
    @Test
    @WithMockUser(username = "user", authorities = {"order:read"}) // User without 'order:confirm' authority
    public void testOrderConfirmAccessWithoutAuthority() throws Exception {
        mockMvc.perform(get("/admin/order/1/confirm"))
                .andExpect(status().isForbidden()); // Access should be denied, expecting 403 Forbidden
    }

    /**
     * Test accessing the /admin/order/{id}/confirm without authentication (anonymous user).
     */
    @Test
    @WithAnonymousUser
    public void testOrderConfirmAccessWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/admin/order/1/confirm"))
                .andExpect(status().is3xxRedirection()); // Should redirect to login page
    }
}
