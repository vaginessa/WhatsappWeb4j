package it.auties.whatsapp.binary;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.regex.Pattern;

/**
 * The constants of this utility class describe the various tokens used by WhatsappWeb's WebSocket.
 * These tags were extracted from JS code found at https://web.whatsapp.com/.
 */
@UtilityClass
public class BinaryTokens {
    /**
     * Single byte tokens
     */
    public final List<String> SINGLE_BYTE = List.of("xmlstreamstart", "xmlstreamend", "s.whatsapp.net", "type", "participant", "from", "receipt", "id", "broadcast", "status", "message", "notification", "notify", "to", "jid", "user", "class", "offline", "g.us", "result", "mediatype", "enc", "skmsg", "off_cnt", "xmlns", "presence", "participants", "ack", "t", "iq", "device_hash", "read", "value", "media", "picture", "chatstate", "unavailable", "text", "urn:xmpp:whatsapp:push", "devices", "verified_name", "contact", "composing", "edge_routing", "routing_info", "item", "image", "verified_level", "get", "fallback_hostname", "2", "media_conn", "1", "v", "handshake", "fallback_class", "count", "config", "offline_preview", "download_buckets", "w:profile:picture", "set", "creation", "location", "fallback_ip4", "msg", "urn:xmpp:ping", "fallback_ip6", "call-creator", "relaylatency", "success", "subscribe", "video", "business_hours_config", "platform", "hostname", "version", "unknown", "0", "ping", "hash", "edit", "subject", "max_buckets", "download", "delivery", "props", "sticker", "name", "last", "contacts", "business", "primary", "preview", "w:p", "pkmsg", "call-id", "retry", "prop", "call", "auth_ttl", "available", "relay_id", "last_id", "day_of_week", "w", "host", "seen", "bits", "list", "atn", "upload", "is_new", "w:stats", "key", "paused", "specific_hours", "multicast", "stream:error", "mmg.whatsapp.net", "code", "deny", "played", "profile", "fna", "device-list", "close_time", "latency", "gcm", "pop", "audio", "26", "w:web", "open_time", "error", "auth", "ip4", "update", "profile_options", "config_value", "category", "catalog_not_created", "00", "config_code", "mode", "catalog_status", "ip6", "blocklist", "registration", "7", "web", "fail", "w:m", "cart_enabled", "ttl", "gif", "300", "device_orientation", "identity", "query", "401", "media-gig2-1.cdn.whatsapp.net", "in", "3", "te2", "add", "fallback", "categories", "ptt", "encrypt", "notice", "thumbnail-document", "item-not-found", "12", "thumbnail-image", "stage", "thumbnail-link", "usync", "out", "thumbnail-video", "8", "01", "context", "sidelist", "thumbnail-gif", "terminate", "not-authorized", "orientation", "dhash", "capability", "side_list", "md-app-state", "description", "serial", "readreceipts", "te", "business_hours", "md-msg-hist", "tag", "attribute_padding", "document", "open_24h", "delete", "expiration", "active", "prev_v_id", "true", "passive", "index", "4", "conflict", "remove", "w:gp2", "config_expo_key", "screen_height", "replaced", "02", "screen_width", "uploadfieldstat", "2:47DEQpj8", "media-bog1-1.cdn.whatsapp.net", "encopt", "url", "catalog_exists", "keygen", "rate", "offer", "opus", "media-mia3-1.cdn.whatsapp.net", "privacy", "media-mia3-2.cdn.whatsapp.net", "signature", "preaccept", "token_id", "media-eze1-1.cdn.whatsapp.net");

    /**
     * Double byte tokens
     */
    public final List<String> DOUBLE_BYTE = List.of("media-for1-1.cdn.whatsapp.net", "relay", "media-gru2-2.cdn.whatsapp.net", "uncompressed", "medium", "voip_settings", "device", "reason", "media-lim1-1.cdn.whatsapp.net", "media-qro1-2.cdn.whatsapp.net", "media-gru1-2.cdn.whatsapp.net", "action", "features", "media-gru2-1.cdn.whatsapp.net", "media-gru1-1.cdn.whatsapp.net", "media-otp1-1.cdn.whatsapp.net", "kyc-id", "priority", "phash", "mute", "token", "100", "media-qro1-1.cdn.whatsapp.net", "none", "media-mrs2-2.cdn.whatsapp.net", "sign_credential", "03", "media-mrs2-1.cdn.whatsapp.net", "protocol", "timezone", "transport", "eph_setting", "1080", "original_dimensions", "media-frx5-1.cdn.whatsapp.net", "background", "disable", "original_image_url", "5", "transaction-id", "direct_path", "103", "appointment_only", "request_image_url", "peer_pid", "address", "105", "104", "102", "media-cdt1-1.cdn.whatsapp.net", "101", "109", "110", "106", "background_location", "v_id", "sync", "status-old", "111", "107", "ppic", "media-scl2-1.cdn.whatsapp.net", "business_profile", "108", "invite", "04", "audio_duration", "media-mct1-1.cdn.whatsapp.net", "media-cdg2-1.cdn.whatsapp.net", "media-los2-1.cdn.whatsapp.net", "invis", "net", "voip_payload_type", "status-revoke-delay", "404", "state", "use_correct_order_for_hmac_sha1", "ver", "media-mad1-1.cdn.whatsapp.net", "order", "540", "skey", "blinded_credential", "android", "contact_remove", "enable_downlink_relay_latency_only", "duration", "enable_vid_one_way_codec_nego", "6", "media-sof1-1.cdn.whatsapp.net", "accept", "all", "signed_credential", "media-atl3-1.cdn.whatsapp.net", "media-lhr8-1.cdn.whatsapp.net", "website", "05", "latitude", "media-dfw5-1.cdn.whatsapp.net", "forbidden", "enable_audio_piggyback_network_mtu_fix", "media-dfw5-2.cdn.whatsapp.net", "note.m4r", "media-atl3-2.cdn.whatsapp.net", "jb_nack_discard_count_fix", "longitude", "Opening.m4r", "media-arn2-1.cdn.whatsapp.net", "email", "timestamp", "admin", "media-pmo1-1.cdn.whatsapp.net", "America/Sao_Paulo", "contact_add", "media-sin6-1.cdn.whatsapp.net", "interactive", "8000", "acs_public_key", "sigquit_anr_detector_release_rollover_percent", "media.fmed1-2.fna.whatsapp.net", "groupadd", "enabled_for_video_upgrade", "latency_update_threshold", "media-frt3-2.cdn.whatsapp.net", "calls_row_constraint_layout", "media.fgbb2-1.fna.whatsapp.net", "mms4_media_retry_notification_encryption_enabled", "timeout", "media-sin6-3.cdn.whatsapp.net", "audio_nack_jitter_multiplier", "jb_discard_count_adjust_pct_rc", "audio_reserve_bps", "delta", "account_sync", "default", "media.fjed4-6.fna.whatsapp.net", "06", "lock_video_orientation", "media-frt3-1.cdn.whatsapp.net", "w:g2", "media-sin6-2.cdn.whatsapp.net", "audio_nack_algo_mask", "media.fgbb2-2.fna.whatsapp.net", "media.fmed1-1.fna.whatsapp.net", "cond_range_target_bitrate", "mms4_server_error_receipt_encryption_enabled", "vid_rc_dyn", "fri", "cart_v1_1_order_message_changes_enabled", "reg_push", "jb_hist_deposit_value", "privatestats", "media.fist7-2.fna.whatsapp.net", "thu", "jb_discard_count_adjust_pct", "mon", "group_call_video_maximization_enabled", "mms_cat_v1_forward_hot_override_enabled", "audio_nack_new_rtt", "media.fsub2-3.fna.whatsapp.net", "media_upload_aggressive_retry_exponential_backoff_enabled", "tue", "wed", "media.fruh4-2.fna.whatsapp.net", "audio_nack_max_seq_req", "max_rtp_audio_packet_resends", "jb_hist_max_cdf_value", "07", "audio_nack_max_jb_delay", "mms_forward_partially_downloaded_video", "media-lcy1-1.cdn.whatsapp.net", "resume", "jb_inband_fec_aware", "new_commerce_entry_point_enabled", "480", "payments_upi_generate_qr_amount_limit", "sigquit_anr_detector_rollover_percent", "media.fsdu2-1.fna.whatsapp.net", "fbns", "aud_pkt_reorder_pct", "dec", "stop_probing_before_accept_send", "media_upload_max_aggressive_retries", "edit_business_profile_new_mode_enabled", "media.fhex4-1.fna.whatsapp.net", "media.fjed4-3.fna.whatsapp.net", "sigquit_anr_detector_64bit_rollover_percent", "cond_range_ema_jb_last_delay", "watls_enable_early_data_http_get", "media.fsdu2-2.fna.whatsapp.net", "message_qr_disambiguation_enabled", "media-mxp1-1.cdn.whatsapp.net", "sat", "vertical", "media.fruh4-5.fna.whatsapp.net", "200", "media-sof1-2.cdn.whatsapp.net", "-1", "height", "product_catalog_hide_show_items_enabled", "deep_copy_frm_last", "tsoffline", "vp8/h.264", "media.fgye5-3.fna.whatsapp.net", "media.ftuc1-2.fna.whatsapp.net", "smb_upsell_chat_banner_enabled", "canonical", "08", "9", ".", "media.fgyd4-4.fna.whatsapp.net", "media.fsti4-1.fna.whatsapp.net", "mms_vcache_aggregation_enabled", "mms_hot_content_timespan_in_seconds", "nse_ver", "rte", "third_party_sticker_web_sync", "cond_range_target_total_bitrate", "media_upload_aggressive_retry_enabled", "instrument_spam_report_enabled", "disable_reconnect_tone", "move_media_folder_from_sister_app", "one_tap_calling_in_group_chat_size", "10", "storage_mgmt_banner_threshold_mb", "enable_backup_passive_mode", "sharechat_inline_player_enabled", "media.fcnq2-1.fna.whatsapp.net", "media.fhex4-2.fna.whatsapp.net", "media.fist6-3.fna.whatsapp.net", "ephemeral_drop_column_stage", "reconnecting_after_network_change_threshold_ms", "media-lhr8-2.cdn.whatsapp.net", "cond_jb_last_delay_ema_alpha", "entry_point_block_logging_enabled", "critical_event_upload_log_config", "respect_initial_bitrate_estimate", "smaller_image_thumbs_status_enabled", "media.fbtz1-4.fna.whatsapp.net", "media.fjed4-1.fna.whatsapp.net", "width", "720", "enable_frame_dropper", "enable_one_side_mode", "urn:xmpp:whatsapp:dirty", "new_sticker_animation_behavior_v2", "media.flim3-2.fna.whatsapp.net", "media.fuio6-2.fna.whatsapp.net", "skip_forced_signaling", "dleq_proof", "status_video_max_bitrate", "lazy_send_probing_req", "enhanced_storage_management", "android_privatestats_endpoint_dit_enabled", "media.fscl13-2.fna.whatsapp.net", "video_duration", "group_call_discoverability_enabled", "media.faep9-2.fna.whatsapp.net", "msgr", "bloks_loggedin_access_app_id", "db_status_migration_step", "watls_prefer_ip6", "jabber:iq:privacy", "68", "media.fsaw1-11.fna.whatsapp.net", "mms4_media_conn_persist_enabled", "animated_stickers_thread_clean_up", "media.fcgk3-2.fna.whatsapp.net", "media.fcgk4-6.fna.whatsapp.net", "media.fgye5-2.fna.whatsapp.net", "media.flpb1-1.fna.whatsapp.net", "media.fsub2-1.fna.whatsapp.net", "media.fuio6-3.fna.whatsapp.net", "not-allowed", "partial_pjpeg_bw_threshold", "cap_estimated_bitrate", "mms_chatd_resume_check_over_thrift", "smb_upsell_business_profile_enabled", "product_catalog_webclient", "groups", "sigquit_anr_detector_release_updated_rollout", "syncd_key_rotation_enabled", "media.fdmm2-1.fna.whatsapp.net", "media-hou1-1.cdn.whatsapp.net", "remove_old_chat_notifications", "smb_biztools_deeplink_enabled", "use_downloadable_filters_int", "group_qr_codes_enabled", "max_receipt_processing_time", "optimistic_image_processing_enabled", "smaller_video_thumbs_status_enabled", "watls_early_data", "reconnecting_before_relay_failover_threshold_ms", "cond_range_packet_loss_pct", "groups_privacy_blacklist", "status-revoke-drop", "stickers_animated_thumbnail_download", "dedupe_transcode_shared_images", "dedupe_transcode_shared_videos", "media.fcnq2-2.fna.whatsapp.net", "media.fgyd4-1.fna.whatsapp.net", "media.fist7-1.fna.whatsapp.net", "media.flim3-3.fna.whatsapp.net", "add_contact_by_qr_enabled", "https://faq.whatsapp.com/payments", "multicast_limit_global", "sticker_notification_preview", "smb_better_catalog_list_adapters_enabled", "bloks_use_minscript_android", "pen_smoothing_enabled", "media.fcgk4-5.fna.whatsapp.net", "media.fevn1-3.fna.whatsapp.net", "media.fpoj7-1.fna.whatsapp.net", "media-arn2-2.cdn.whatsapp.net", "reconnecting_before_network_change_threshold_ms", "android_media_use_fresco_for_gifs", "cond_in_congestion", "status_image_max_edge", "sticker_search_enabled", "starred_stickers_web_sync", "db_blank_me_jid_migration_step", "media.fist6-2.fna.whatsapp.net", "media.ftuc1-1.fna.whatsapp.net", "09", "anr_fast_logs_upload_rollout", "camera_core_integration_enabled", "11", "third_party_sticker_caching", "thread_dump_contact_support", "wam_privatestats_enabled", "vcard_as_document_size_kb", "maxfpp", "fbip", "ephemeral_allow_group_members", "media-bom1-2.cdn.whatsapp.net", "media-xsp1-1.cdn.whatsapp.net", "disable_prewarm", "frequently_forwarded_max", "media.fbtz1-5.fna.whatsapp.net", "media.fevn7-1.fna.whatsapp.net", "media.fgyd4-2.fna.whatsapp.net", "sticker_tray_animation_fully_visible_items", "green_alert_banner_duration", "reconnecting_after_p2p_failover_threshold_ms", "connected", "share_biz_vcard_enabled", "stickers_animation", "0a", "1200", "WhatsApp", "group_description_length", "p_v_id", "payments_upi_intent_transaction_limit", "frequently_forwarded_messages", "media-xsp1-2.cdn.whatsapp.net", "media.faep8-1.fna.whatsapp.net", "media.faep8-2.fna.whatsapp.net", "media.faep9-1.fna.whatsapp.net", "media.fdmm2-2.fna.whatsapp.net", "media.fgzt3-1.fna.whatsapp.net", "media.flim4-2.fna.whatsapp.net", "media.frao1-1.fna.whatsapp.net", "media.fscl9-2.fna.whatsapp.net", "media.fsub2-2.fna.whatsapp.net", "superadmin", "media.fbog10-1.fna.whatsapp.net", "media.fcgh28-1.fna.whatsapp.net", "media.fjdo10-1.fna.whatsapp.net", "third_party_animated_sticker_import", "delay_fec", "attachment_picker_refresh", "android_linked_devices_re_auth_enabled", "rc_dyn", "green_alert_block_jitter", "add_contact_logging_enabled", "biz_message_logging_enabled", "conversation_media_preview_v2", "media-jnb1-1.cdn.whatsapp.net", "ab_key", "media.fcgk4-2.fna.whatsapp.net", "media.fevn1-1.fna.whatsapp.net", "media.fist6-1.fna.whatsapp.net", "media.fruh4-4.fna.whatsapp.net", "media.fsti4-2.fna.whatsapp.net", "mms_vcard_autodownload_size_kb", "watls_enabled", "notif_ch_override_off", "media.fsaw1-14.fna.whatsapp.net", "media.fscl13-1.fna.whatsapp.net", "db_group_participant_migration_step", "1020", "cond_range_sterm_rtt", "invites_logging_enabled", "triggered_block_enabled", "group_call_max_participants", "media-iad3-1.cdn.whatsapp.net", "product_catalog_open_deeplink", "shops_required_tos_version", "image_max_kbytes", "cond_low_quality_vid_mode", "db_receipt_migration_step", "jb_early_prob_hist_shrink", "media.fdmm2-3.fna.whatsapp.net", "media.fdmm2-4.fna.whatsapp.net", "media.fruh4-1.fna.whatsapp.net", "media.fsaw2-2.fna.whatsapp.net", "remove_geolocation_videos", "new_animation_behavior", "fieldstats_beacon_chance", "403", "authkey_reset_on_ban", "continuous_ptt_playback", "reconnecting_after_relay_failover_threshold_ms", "false", "group", "sun", "conversation_swipe_to_reply", "ephemeral_messages_setting", "smaller_video_thumbs_enabled", "md_device_sync_enabled", "bloks_shops_pdp_url_regex", "lasso_integration_enabled", "media-bom1-1.cdn.whatsapp.net", "new_backup_format_enabled", "256", "media.faep6-1.fna.whatsapp.net", "media.fasr1-1.fna.whatsapp.net", "media.fbtz1-7.fna.whatsapp.net", "media.fesb4-1.fna.whatsapp.net", "media.fjdo1-2.fna.whatsapp.net", "media.frba2-1.fna.whatsapp.net", "watls_no_dns", "600", "db_broadcast_me_jid_migration_step", "new_wam_runtime_enabled", "group_update", "enhanced_block_enabled", "sync_wifi_threshold_kb", "mms_download_nc_cat", "bloks_minification_enabled", "ephemeral_messages_enabled", "reject", "voip_outgoing_xml_signaling", "creator", "dl_bw", "payments_request_messages", "target_bitrate", "bloks_rendercore_enabled", "media-hbe1-1.cdn.whatsapp.net", "media-hel3-1.cdn.whatsapp.net", "media-kut2-2.cdn.whatsapp.net", "media-lax3-1.cdn.whatsapp.net", "media-lax3-2.cdn.whatsapp.net", "sticker_pack_deeplink_enabled", "hq_image_bw_threshold", "status_info", "voip", "dedupe_transcode_videos", "grp_uii_cleanup", "linked_device_max_count", "media.flim1-1.fna.whatsapp.net", "media.fsaw2-1.fna.whatsapp.net", "reconnecting_after_call_active_threshold_ms", "1140", "catalog_pdp_new_design", "media.fbtz1-10.fna.whatsapp.net", "media.fsaw1-15.fna.whatsapp.net", "0b", "consumer_rc_provider", "mms_async_fast_forward_ttl", "jb_eff_size_fix", "voip_incoming_xml_signaling", "media_provider_share_by_uuid", "suspicious_links", "dedupe_transcode_images", "green_alert_modal_start", "media-cgk1-1.cdn.whatsapp.net", "media-lga3-1.cdn.whatsapp.net", "template_doc_mime_types", "important_messages", "user_add", "vcard_max_size_kb", "media.fada2-1.fna.whatsapp.net", "media.fbog2-5.fna.whatsapp.net", "media.fbtz1-3.fna.whatsapp.net", "media.fcgk3-1.fna.whatsapp.net", "media.fcgk7-1.fna.whatsapp.net", "media.flim1-3.fna.whatsapp.net", "media.fscl9-1.fna.whatsapp.net", "ctwa_context_enterprise_enabled", "media.fsaw1-13.fna.whatsapp.net", "media.fuio11-2.fna.whatsapp.net", "status_collapse_muted", "db_migration_level_force", "recent_stickers_web_sync", "bloks_session_state", "bloks_shops_enabled", "green_alert_setting_deep_links_enabled", "restrict_groups", "battery", "green_alert_block_start", "refresh", "ctwa_context_enabled", "md_messaging_enabled", "status_image_quality", "md_blocklist_v2_server", "media-del1-1.cdn.whatsapp.net", "13", "userrate", "a_v_id", "cond_rtt_ema_alpha", "invalid", "media.fada1-1.fna.whatsapp.net", "media.fadb3-2.fna.whatsapp.net", "media.fbhz2-1.fna.whatsapp.net", "media.fcor2-1.fna.whatsapp.net", "media.fjed4-2.fna.whatsapp.net", "media.flhe4-1.fna.whatsapp.net", "media.frak1-2.fna.whatsapp.net", "media.fsub6-3.fna.whatsapp.net", "media.fsub6-7.fna.whatsapp.net", "media.fvvi1-1.fna.whatsapp.net", "search_v5_eligible", "wam_real_time_enabled", "report_disk_event", "max_tx_rott_based_bitrate", "product", "media.fjdo10-2.fna.whatsapp.net", "video_frame_crc_sample_interval", "media_max_autodownload", "15", "h.264", "wam_privatestats_buffer_count", "md_phash_v2_enabled", "account_transfer_enabled", "business_product_catalog", "enable_non_dyn_codec_param_fix", "is_user_under_epd_jurisdiction", "media.fbog2-4.fna.whatsapp.net", "media.fbtz1-2.fna.whatsapp.net", "media.fcfc1-1.fna.whatsapp.net", "media.fjed4-5.fna.whatsapp.net", "media.flhe4-2.fna.whatsapp.net", "media.flim1-2.fna.whatsapp.net", "media.flos5-1.fna.whatsapp.net", "android_key_store_auth_ver", "010", "anr_process_monitor", "delete_old_auth_key", "media.fcor10-3.fna.whatsapp.net", "storage_usage_enabled", "android_camera2_support_level", "dirty", "consumer_content_provider", "status_video_max_duration", "0c", "bloks_cache_enabled", "media.fadb2-2.fna.whatsapp.net", "media.fbko1-1.fna.whatsapp.net", "media.fbtz1-9.fna.whatsapp.net", "media.fcgk4-4.fna.whatsapp.net", "media.fesb4-2.fna.whatsapp.net", "media.fevn1-2.fna.whatsapp.net", "media.fist2-4.fna.whatsapp.net", "media.fjdo1-1.fna.whatsapp.net", "media.fruh4-6.fna.whatsapp.net", "media.fsrg5-1.fna.whatsapp.net", "media.fsub6-6.fna.whatsapp.net", "minfpp", "5000", "locales", "video_max_bitrate", "use_new_auth_key", "bloks_http_enabled", "heartbeat_interval", "media.fbog11-1.fna.whatsapp.net", "ephemeral_group_query_ts", "fec_nack", "search_in_storage_usage", "c", "media-amt2-1.cdn.whatsapp.net", "linked_devices_ui_enabled", "14", "async_data_load_on_startup", "voip_incoming_xml_ack", "16", "db_migration_step", "init_bwe", "max_participants", "wam_buffer_count", "media.fada2-2.fna.whatsapp.net", "media.fadb3-1.fna.whatsapp.net", "media.fcor2-2.fna.whatsapp.net", "media.fdiy1-2.fna.whatsapp.net", "media.frba3-2.fna.whatsapp.net", "media.fsaw2-3.fna.whatsapp.net", "1280", "status_grid_enabled", "w:biz", "product_catalog_deeplink", "media.fgye10-2.fna.whatsapp.net", "media.fuio11-1.fna.whatsapp.net", "optimistic_upload", "work_manager_init", "lc", "catalog_message", "cond_net_medium", "enable_periodical_aud_rr_processing", "cond_range_ema_rtt", "media-tir2-1.cdn.whatsapp.net", "frame_ms", "group_invite_sending", "payments_web_enabled", "wallpapers_v2", "0d", "browser", "hq_image_max_edge", "image_edit_zoom", "linked_devices_re_auth_enabled", "media.faly3-2.fna.whatsapp.net", "media.fdoh5-3.fna.whatsapp.net", "media.fesb3-1.fna.whatsapp.net", "media.fknu1-1.fna.whatsapp.net", "media.fmex3-1.fna.whatsapp.net", "media.fruh4-3.fna.whatsapp.net", "255", "web_upgrade_to_md_modal", "audio_piggyback_timeout_msec", "enable_audio_oob_fec_feature", "from_ip", "image_max_edge", "message_qr_enabled", "powersave", "receipt_pre_acking", "video_max_edge", "full", "011", "012", "enable_audio_oob_fec_for_sender", "md_voip_enabled", "enable_privatestats", "max_fec_ratio", "payments_cs_faq_url", "media-xsp1-3.cdn.whatsapp.net", "hq_image_quality", "media.fasr1-2.fna.whatsapp.net", "media.fbog3-1.fna.whatsapp.net", "media.ffjr1-6.fna.whatsapp.net", "media.fist2-3.fna.whatsapp.net", "media.flim4-3.fna.whatsapp.net", "media.fpbc2-4.fna.whatsapp.net", "media.fpku1-1.fna.whatsapp.net", "media.frba1-1.fna.whatsapp.net", "media.fudi1-1.fna.whatsapp.net", "media.fvvi1-2.fna.whatsapp.net", "gcm_fg_service", "enable_dec_ltr_size_check", "clear", "lg", "media.fgru11-1.fna.whatsapp.net", "18", "media-lga3-2.cdn.whatsapp.net", "pkey", "0e", "max_subject", "cond_range_lterm_rtt", "announcement_groups", "biz_profile_options", "s_t", "media.fabv2-1.fna.whatsapp.net", "media.fcai3-1.fna.whatsapp.net", "media.fcgh1-1.fna.whatsapp.net", "media.fctg1-4.fna.whatsapp.net", "media.fdiy1-1.fna.whatsapp.net", "media.fisb4-1.fna.whatsapp.net", "media.fpku1-2.fna.whatsapp.net", "media.fros9-1.fna.whatsapp.net", "status_v3_text", "usync_sidelist", "17", "announcement", "...", "md_group_notification", "0f", "animated_pack_in_store", "013", "America/Mexico_City", "1260", "media-ams4-1.cdn.whatsapp.net", "media-cgk1-2.cdn.whatsapp.net", "media-cpt1-1.cdn.whatsapp.net", "media-maa2-1.cdn.whatsapp.net", "media.fgye10-1.fna.whatsapp.net", "e", "catalog_cart", "hfm_string_changes", "init_bitrate", "packless_hsm", "group_info", "America/Belem", "50", "960", "cond_range_bwe", "decode", "encode", "media.fada1-8.fna.whatsapp.net", "media.fadb1-2.fna.whatsapp.net", "media.fasu6-1.fna.whatsapp.net", "media.fbog4-1.fna.whatsapp.net", "media.fcgk9-2.fna.whatsapp.net", "media.fdoh5-2.fna.whatsapp.net", "media.ffjr1-2.fna.whatsapp.net", "media.fgua1-1.fna.whatsapp.net", "media.fgye1-1.fna.whatsapp.net", "media.fist1-4.fna.whatsapp.net", "media.fpbc2-2.fna.whatsapp.net", "media.fres2-1.fna.whatsapp.net", "media.fsdq1-2.fna.whatsapp.net", "media.fsub6-5.fna.whatsapp.net", "profilo_enabled", "template_hsm", "use_disorder_prefetching_timer", "video_codec_priority", "vpx_max_qp", "ptt_reduce_recording_delay", "25", "iphone", "Windows", "s_o", "Africa/Lagos", "abt", "media-kut2-1.cdn.whatsapp.net", "media-mba1-1.cdn.whatsapp.net", "media-mxp1-2.cdn.whatsapp.net", "md_blocklist_v2", "url_text", "enable_short_offset", "group_join_permissions", "enable_audio_piggyback_feature", "image_quality", "media.fcgk7-2.fna.whatsapp.net", "media.fcgk8-2.fna.whatsapp.net", "media.fclo7-1.fna.whatsapp.net", "media.fcmn1-1.fna.whatsapp.net", "media.feoh1-1.fna.whatsapp.net", "media.fgyd4-3.fna.whatsapp.net", "media.fjed4-4.fna.whatsapp.net", "media.flim1-4.fna.whatsapp.net", "media.flim2-4.fna.whatsapp.net", "media.fplu6-1.fna.whatsapp.net", "media.frak1-1.fna.whatsapp.net", "media.fsdq1-1.fna.whatsapp.net", "to_ip", "015", "vp8", "19", "21", "1320", "auth_key_ver", "message_processing_dedup", "server-error", "wap4_enabled", "420", "014", "cond_range_rtt", "ptt_fast_lock_enabled", "media-ort2-1.cdn.whatsapp.net", "fwd_ui_start_ts", "contact_blacklist", "Asia/Jakarta", "media.fepa10-1.fna.whatsapp.net", "media.fmex10-3.fna.whatsapp.net", "disorder_prefetching_start_when_empty", "America/Bogota", "use_local_probing_rx_bitrate", "America/Argentina/Buenos_Aires", "cross_post", "media.fabb1-1.fna.whatsapp.net", "media.fbog4-2.fna.whatsapp.net", "media.fcgk9-1.fna.whatsapp.net", "media.fcmn2-1.fna.whatsapp.net", "media.fdel3-1.fna.whatsapp.net", "media.ffjr1-1.fna.whatsapp.net", "media.fgdl5-1.fna.whatsapp.net", "media.flpb1-2.fna.whatsapp.net", "media.fmex2-1.fna.whatsapp.net", "media.frba2-2.fna.whatsapp.net", "media.fros2-2.fna.whatsapp.net", "media.fruh2-1.fna.whatsapp.net", "media.fybz2-2.fna.whatsapp.net", "options", "20", "a", "017", "018", "mute_always", "user_notice", "Asia/Kolkata", "gif_provider", "locked", "media-gua1-1.cdn.whatsapp.net", "piggyback_exclude_force_flush", "24", "media.frec39-1.fna.whatsapp.net", "user_remove", "file_max_size", "cond_packet_loss_pct_ema_alpha", "media.facc1-1.fna.whatsapp.net", "media.fadb2-1.fna.whatsapp.net", "media.faly3-1.fna.whatsapp.net", "media.fbdo6-2.fna.whatsapp.net", "media.fcmn2-2.fna.whatsapp.net", "media.fctg1-3.fna.whatsapp.net", "media.ffez1-2.fna.whatsapp.net", "media.fist1-3.fna.whatsapp.net", "media.fist2-2.fna.whatsapp.net", "media.flim2-2.fna.whatsapp.net", "media.fmct2-3.fna.whatsapp.net", "media.fpei3-1.fna.whatsapp.net", "media.frba3-1.fna.whatsapp.net", "media.fsdu8-2.fna.whatsapp.net", "media.fstu2-1.fna.whatsapp.net", "media_type", "receipt_agg", "016", "enable_pli_for_crc_mismatch", "live", "enc_rekey", "frskmsg", "d", "media.fdel11-2.fna.whatsapp.net", "proto", "2250", "audio_piggyback_enable_cache", "skip_nack_if_ltrp_sent", "mark_dtx_jb_frames", "web_service_delay", "7282", "catalog_send_all", "outgoing", "360", "30", "LIMITED", "019", "audio_picker", "bpv2_phase", "media.fada1-7.fna.whatsapp.net", "media.faep7-1.fna.whatsapp.net", "media.fbko1-2.fna.whatsapp.net", "media.fbni1-2.fna.whatsapp.net", "media.fbtz1-1.fna.whatsapp.net", "media.fbtz1-8.fna.whatsapp.net", "media.fcjs3-1.fna.whatsapp.net", "media.fesb3-2.fna.whatsapp.net", "media.fgdl5-4.fna.whatsapp.net", "media.fist2-1.fna.whatsapp.net", "media.flhe2-2.fna.whatsapp.net", "media.flim2-1.fna.whatsapp.net", "media.fmex1-1.fna.whatsapp.net", "media.fpat3-2.fna.whatsapp.net", "media.fpat3-3.fna.whatsapp.net", "media.fros2-1.fna.whatsapp.net", "media.fsdu8-1.fna.whatsapp.net", "media.fsub3-2.fna.whatsapp.net", "payments_chat_plugin", "cond_congestion_no_rtcp_thr", "green_alert", "not-a-biz", "..", "shops_pdp_urls_config", "source", "media-dus1-1.cdn.whatsapp.net", "mute_video", "01b", "currency", "max_keys", "resume_check", "contact_array", "qr_scanning", "23", "b", "media.fbfh15-1.fna.whatsapp.net", "media.flim22-1.fna.whatsapp.net", "media.fsdu11-1.fna.whatsapp.net", "media.fsdu15-1.fna.whatsapp.net", "Chrome", "fts_version", "60", "media.fada1-6.fna.whatsapp.net", "media.faep4-2.fna.whatsapp.net", "media.fbaq5-1.fna.whatsapp.net", "media.fbni1-1.fna.whatsapp.net", "media.fcai3-2.fna.whatsapp.net", "media.fdel3-2.fna.whatsapp.net", "media.fdmm3-2.fna.whatsapp.net", "media.fhex3-1.fna.whatsapp.net", "media.fisb4-2.fna.whatsapp.net", "media.fkhi5-2.fna.whatsapp.net", "media.flos2-1.fna.whatsapp.net", "media.fmct2-1.fna.whatsapp.net", "media.fntr7-1.fna.whatsapp.net", "media.frak3-1.fna.whatsapp.net", "media.fruh5-2.fna.whatsapp.net", "media.fsub6-1.fna.whatsapp.net", "media.fuab1-2.fna.whatsapp.net", "media.fuio1-1.fna.whatsapp.net", "media.fver1-1.fna.whatsapp.net", "media.fymy1-1.fna.whatsapp.net", "product_catalog", "1380", "audio_oob_fec_max_pkts", "22", "254", "media-ort2-2.cdn.whatsapp.net", "media-sjc3-1.cdn.whatsapp.net", "1600", "01a", "01c", "405", "key_frame_interval", "body", "media.fcgh20-1.fna.whatsapp.net", "media.fesb10-2.fna.whatsapp.net", "125", "2000", "media.fbsb1-1.fna.whatsapp.net", "media.fcmn3-2.fna.whatsapp.net", "media.fcpq1-1.fna.whatsapp.net", "media.fdel1-2.fna.whatsapp.net", "media.ffor2-1.fna.whatsapp.net", "media.fgdl1-4.fna.whatsapp.net", "media.fhex2-1.fna.whatsapp.net", "media.fist1-2.fna.whatsapp.net", "media.fjed5-2.fna.whatsapp.net", "media.flim6-4.fna.whatsapp.net", "media.flos2-2.fna.whatsapp.net", "media.fntr6-2.fna.whatsapp.net", "media.fpku3-2.fna.whatsapp.net", "media.fros8-1.fna.whatsapp.net", "media.fymy1-2.fna.whatsapp.net", "ul_bw", "ltrp_qp_offset", "request", "nack", "dtx_delay_state_reset", "timeoffline", "28", "01f", "32", "enable_ltr_pool", "wa_msys_crypto", "01d", "58", "dtx_freeze_hg_update", "nack_if_rpsi_throttled", "253", "840", "media.famd15-1.fna.whatsapp.net", "media.fbog17-2.fna.whatsapp.net", "media.fcai19-2.fna.whatsapp.net", "media.fcai21-4.fna.whatsapp.net", "media.fesb10-4.fna.whatsapp.net", "media.fesb10-5.fna.whatsapp.net", "media.fmaa12-1.fna.whatsapp.net", "media.fmex11-3.fna.whatsapp.net", "media.fpoa33-1.fna.whatsapp.net", "1050", "021", "clean", "cond_range_ema_packet_loss_pct", "media.fadb6-5.fna.whatsapp.net", "media.faqp4-1.fna.whatsapp.net", "media.fbaq3-1.fna.whatsapp.net", "media.fbel2-1.fna.whatsapp.net", "media.fblr4-2.fna.whatsapp.net", "media.fclo8-1.fna.whatsapp.net", "media.fcoo1-2.fna.whatsapp.net", "media.ffjr1-4.fna.whatsapp.net", "media.ffor9-1.fna.whatsapp.net", "media.fisb3-1.fna.whatsapp.net", "media.fkhi2-2.fna.whatsapp.net", "media.fkhi4-1.fna.whatsapp.net", "media.fpbc1-2.fna.whatsapp.net", "media.fruh2-2.fna.whatsapp.net", "media.fruh5-1.fna.whatsapp.net", "media.fsub3-1.fna.whatsapp.net", "payments_transaction_limit", "252", "27", "29", "tintagel", "01e", "237", "780", "callee_updated_payload", "020", "257", "price", "025", "239", "payments_cs_phone_number", "mediaretry", "w:auth:backup:token", "Glass.caf", "max_bitrate", "240", "251", "660", "media.fbog16-1.fna.whatsapp.net", "media.fcgh21-1.fna.whatsapp.net", "media.fkul19-2.fna.whatsapp.net", "media.flim21-2.fna.whatsapp.net", "media.fmex10-4.fna.whatsapp.net", "64", "33", "34", "35", "interruption", "media.fabv3-1.fna.whatsapp.net", "media.fadb6-1.fna.whatsapp.net", "media.fagr1-1.fna.whatsapp.net", "media.famd1-1.fna.whatsapp.net", "media.famm6-1.fna.whatsapp.net", "media.faqp2-3.fna.whatsapp.net");

    /**
     * Numeric tokens
     */
    public final List<Character> NUMBERS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '.', '�', '�', '�', '�');

    /**
     * Hex tokens
     */
    public final List<Character> HEX = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');

    /**
     * Numeric tokens inverse regex
     */
    public final String NUMBERS_REGEX = "[^0-9.-]+?";

    /**
     * Hex tokens inverse regex
     */
    public final String HEX_REGEX = "[^0-9A-F]+?";

    /**
     * Checks if the provided regex has no matches with the provided string
     *
     * @param input the non-null input string
     * @param regex the non-null regex
     * @return true if no matches are found
     */
    public boolean checkRegex(@NonNull String input, @NonNull String regex) {
        return Pattern.compile(regex)
                .matcher(input)
                .results()
                .findAny()
                .isEmpty();
    }
}
