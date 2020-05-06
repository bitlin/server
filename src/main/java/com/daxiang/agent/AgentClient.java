package com.daxiang.agent;

import com.alibaba.fastjson.JSONObject;
import com.daxiang.mbg.po.Browser;
import com.daxiang.mbg.po.Mobile;
import com.daxiang.model.Response;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jiangyitao.
 */
@Component
public class AgentClient {

    @Autowired
    private RestTemplate restTemplate;

    public Response debugAction(String agentIp, int agentPort, JSONObject requestBody) {
        String url = getUrl(agentIp, agentPort, "/action/debug");
        return restTemplate.postForObject(url, requestBody, Response.class);
    }

    /**
     * dump apk信息
     *
     * @param agentIp
     * @param agentPort
     * @param apkDownloadUrl
     * @return
     */
    public Response aaptDumpBadging(String agentIp, int agentPort, String apkDownloadUrl) {
        String url = getUrl(agentIp, agentPort, "/android/aaptDumpBadging");
        return restTemplate.postForObject(url, apkDownloadUrl, Response.class);
    }

    public Response<Mobile> getMobileStatus(String agentIp, int agentPort, String mobileId) {
        String url = getUrl(agentIp, agentPort, "/mobile/status?mobileId={mobileId}");
        return restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Response<Mobile>>() {
                },
                ImmutableMap.of("mobileId", mobileId)).getBody();
    }

    public Response<Browser> getBrowserStatus(String agentIp, int agentPort, String browserId) {
        String url = getUrl(agentIp, agentPort, "/browser/status?browserId={browserId}");
        return restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Response<Browser>>() {
                },
                ImmutableMap.of("browserId", browserId)).getBody();
    }

    private String getUrl(String agentIp, int agentPort, String requestURI) {
        return String.format("http://%s:%d%s", agentIp, agentPort, requestURI);
    }
}
