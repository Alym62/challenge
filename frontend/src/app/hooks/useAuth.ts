"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export const useAuth = () => {
    const [loading, setLoading] = useState(true);
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const token = localStorage.getItem("accessToken");
        if (!token) {
            router.push("/auth");
        } else {
            setIsAuthenticated(true);
        }
        setLoading(false);
    }, []);

    return { isAuthenticated, loading };
};
