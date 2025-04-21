import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "../styles/MainPage.css";

const API_URL = "http://localhost:8080";

const MainPage = () => {
    const [newsList, setNewsList] = useState([]);

    useEffect(() => {
        fetch(`${API_URL}/news`)
            .then((res) => res.json())
            .then(async (data) => {
                const enriched = await Promise.all(
                    data.map(async (news) => {
                        const firstImageId = news.imageIdList[0];
                        if (firstImageId) {
                            const img = await fetch(`${API_URL}/image/${firstImageId}`).then((r) => r.json());
                            return { ...news, imagePath: img.image };
                        }
                        return { ...news, imagePath: null };
                    })
                );
                setNewsList(enriched);
            });
    }, []);

    return (
        <div className="main-page">
            {newsList.map((news) => (
                <div key={news.id} className="news-card">
                    {news.imagePath && (
                        <>
                            <div className="main-page_img-container">
                                <img src={API_URL + news.imagePath} alt="news" />
                            </div>
                        </>
                    )}
                    <Link to={`/news/${news.id}`} className="news-title">
                        {news.title}
                    </Link>
                </div>
            ))}
        </div>
    );
};

export default MainPage;
