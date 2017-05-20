﻿using System;

namespace WebShop.BL.BusinessLayerContracts.DTOs
{
    class OrderDTO
        {
            public int Id { get; set; }
            public string Name { get; set; }
            public string Address { get; set; }

            public DateTime Date { get; set; }
        }
}