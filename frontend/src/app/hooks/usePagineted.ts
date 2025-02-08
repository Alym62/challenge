'use client';

import { ContatoModel } from "@models/contato.model";
import { api } from "@service/api";
import { message } from "antd";
import { useEffect, useState } from "react";

export const usePaginatedData = (endpoint: string, pageSize = 10) => {
    const [data, setData] = useState<Array<ContatoModel>>(new Array<ContatoModel>());
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(0);
    const [total, setTotal] = useState(0);
    const [pageSizeState, setPageSizeState] = useState(pageSize);

    const fetchData = async (filters = {}) => {
        setLoading(true);
        try {
            const response = await api.get(endpoint, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('accessToken')}`
                },
                params: {
                    page: page,
                    perPage: pageSizeState,
                    ...filters
                },
            });

            setData(response.data.content);
            setTotal(response.data.totalElements);
        } catch (error) {
            message.error('Ops! Não foi possível buscar os dados no momento.')
        }
        setLoading(false);
    };

    useEffect(() => {
        fetchData();
    }, [endpoint, page, pageSizeState]);

    const refetch = (filters = {}) => {
        fetchData(filters);
    };

    return { data, loading, page, setPage, total, pageSizeState, setPageSizeState, refetch };
};